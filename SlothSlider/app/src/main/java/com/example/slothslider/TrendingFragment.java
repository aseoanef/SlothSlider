package com.example.slothslider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrendingFragment extends Fragment {
    private static final String TEXT_ID = "text_id";
    private Intent intent;
    public static TrendingFragment newInstance(@StringRes int textId) {
       TrendingFragment frag = new TrendingFragment();

        Bundle args = new Bundle();
        args.putInt(TEXT_ID, textId);
        frag.setArguments(args);

        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.trending_fragment, container, false);
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://raw.githubusercontent.com/aseoanef/SlothSlider/main/Moviesjson",
                null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getActivity(), "Imagenes recibidas", Toast.LENGTH_SHORT).show();
                        // Procesar la respuesta JSON y crear una lista de objetos FilmData con los datos de la URL.
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject film = null;
                            try {
                                film = response.getJSONObject(i);
                                String imageurl = film.getString("image");
                                switch (i){
                                    case 0:{
                                        ImageView image1=layout.findViewById(R.id.pelicula1);
                                        Util.downloadBitmapToImageView(imageurl,image1);
                                        break;
                                    }
                                    case 1:{
                                        ImageView image2=layout.findViewById(R.id.pelicula2);
                                        Util.downloadBitmapToImageView(imageurl,image2);
                                        break;
                                    }
                                    case 2:{
                                        ImageView image3=layout.findViewById(R.id.pelicula3);
                                        Util.downloadBitmapToImageView(imageurl,image3);
                                        break;
                                    }
                                    case 3:{
                                        ImageView image4=layout.findViewById(R.id.pelicula4);
                                        Util.downloadBitmapToImageView(imageurl,image4);
                                        break;
                                    }
                                    case 4:{
                                        ImageView image5=layout.findViewById(R.id.pelicula5);
                                        Util.downloadBitmapToImageView(imageurl,image5);
                                        break;
                                    }
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar los errores de la solicitud y mostrar un Toast con el mensaje de error.
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        // Agregar la solicitud a la cola de Volley para su procesamiento.
        RequestQueue queue = Volley.newRequestQueue(layout.getContext());
        queue.add(request);
        return layout;
    }
}