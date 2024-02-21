package com.example.slothslider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
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

import java.util.ArrayList;
import java.util.List;

public class BuscarFragment extends Fragment implements SearchView.OnQueryTextListener {
    private static final String TEXT_ID = "text_id";
    private Intent intent;
    private ArrayList<FilmData> arrayList;

    private MovieViewAdapter adapter;
    private String movie_title;
    private android.widget.SearchView buscador;
    // Método estático para crear una instancia del fragmento
    public static BuscarFragment newInstance(@StringRes int textId) {
        BuscarFragment frag = new BuscarFragment();

        Bundle args = new Bundle();
        args.putInt(TEXT_ID, textId);
        frag.setArguments(args);

        return frag;
    }

    @Override
    // Método llamado al crear la vista del fragmento
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.buscar_fragment, container, false);
        // Obtener películas de la API
        getMovies(layout);
        // Configurar la barra de búsqueda
        buscador = (SearchView) layout.findViewById(R.id.searchView);
        buscador.setOnQueryTextListener(this);
        // Configurar la lista de películas
        ListView list = (ListView) layout.findViewById(R.id.listview);
        arrayList = new ArrayList<>();// Inicializar la lista de películas
        Bundle bundle = intent.getExtras();// Obtener datos extras del intent
        if (bundle!=null){
            movie_title = (String) bundle.getString("name");// Obtener el título de la película
        }
        adapter = new MovieViewAdapter(arrayList,getActivity());

        // Mostrar o ocultar la lista de películas según si está vacía
        if (arrayList.isEmpty()) {
            list.setVisibility(View.GONE);
        } else {
            list.setVisibility(View.VISIBLE);
            list.setAdapter(adapter);
        }
        return layout;
    }
    // Método para obtener películas de la API
    public void getMovies(View layout){
        // Crear una solicitud para obtener películas de la API
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://raw.githubusercontent.com/aseoanef/SlothSlider/main/Moviesjson",
                null,
                new Response.Listener<JSONArray>(){
                    @Override
                    // Manejar la respuesta exitosa de la solicitud
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getActivity(), "Imagenes recibidas", Toast.LENGTH_SHORT).show();
                        // Procesar la respuesta JSON y crear una lista de objetos FilmData con los datos de la URL.
                        List<FilmData> allThefilms = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject film = response.getJSONObject(i);
                                FilmData data = new FilmData(film);
                                allThefilms.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    // Manejar errores de la solicitud
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
    }
    // Método llamado al enviar la consulta de búsqueda
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    // Método llamado al cambiar el texto de búsqueda
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}


