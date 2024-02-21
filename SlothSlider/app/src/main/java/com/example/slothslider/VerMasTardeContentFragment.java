package com.example.slothslider;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.Nullable;
        import androidx.annotation.StringRes;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

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

public class VerMasTardeContentFragment extends Fragment {
    private static final String TEXT_ID = "text_id";
    private Intent intent;
    private Activity activity = getActivity();
    private RecyclerView recyclerView;


    public static VerMasTardeContentFragment newInstance(@StringRes int textId) {
        VerMasTardeContentFragment frag = new VerMasTardeContentFragment();

        Bundle args = new Bundle();
        args.putInt(TEXT_ID, textId);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.ver_mas_tarde, container, false);
        recyclerView = layout.findViewById(R.id.VMT_recycler); // Obtener una referencia al RecyclerView desde el diseño

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://raw.githubusercontent.com/aseoanef/SlothSlider/rama-gael/SeeLaterFilmsJSON",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        List<ShowLaterMovie> allThefilms = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject film = response.getJSONObject(i);
                                ShowLaterMovie data = new ShowLaterMovie(film);
                                allThefilms.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // Crear un adaptador con la lista de datos y la actividad asociada.
                        VMTRecyclerViewAdapter adapter = new VMTRecyclerViewAdapter(allThefilms, activity);

                        // Configurar el RecyclerView con el adaptador y un LinearLayoutManager.
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar los errores de la solicitud y mostrar un Toast con el mensaje de error.
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        // Agregar la solicitud a la cola de Volley para su procesamiento.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

        if (getArguments() != null) {
            String text = getString(getArguments().getInt(TEXT_ID));
            ((TextView) layout.findViewById(R.id.header_title)).setText(text);
        } else {
            throw new IllegalArgumentException("Argument " + TEXT_ID + " is mandatory");
        }


        return layout;
    }
}








