package com.example.slothslider;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddMovieReview extends Fragment {
    private static final String TEXT_ID = "text_id";
    private String title;
    private Intent intent;
    public static AddMovieReview newInstance(@StringRes int textId) {
        AddMovieReview frag = new AddMovieReview();
        Bundle args = new Bundle();
        args.putInt(TEXT_ID, textId);
        frag.setArguments(args);

        return frag;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }
    public Integer getScore(View layout){
        RadioButton button1=layout.findViewById(R.id.scorenumber1);
        RadioButton button2=layout.findViewById(R.id.scorenumber2);
        RadioButton button3=layout.findViewById(R.id.scorenumber3);
        RadioButton button4=layout.findViewById(R.id.scorenumber4);
        RadioButton button5=layout.findViewById(R.id.scorenumber5);
        if (button1.isChecked()){
            return 1;
        } else if (button2.isChecked()) {
            return 2;
        }else if (button3.isChecked()) {
            return 3;
        }else if (button4.isChecked()) {
            return 4;
        }else if (button5.isChecked()) {
            return 5;
        }else{
            return 0;
        }
    }
    public void sendReview(View layout){
        EditText review=layout.findViewById(R.id.reviewtext);
        TextView title=layout.findViewById(R.id.title);
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("content", review.getText().toString());
            requestBody.put("title",title.getText().toString());
            requestBody.put("score",getScore(layout));
        }catch (JSONException e){
            throw new RuntimeException(e);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "endpoint",
                requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Aquí mostramos un Toast
                        // que indique "Hit OK"
                        Toast.makeText(layout.getContext(), "Review send", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse == null) {
                    // Error: No se ha establecido la conexión
                    Toast.makeText(layout.getContext(), "Connection could not be established", Toast.LENGTH_SHORT).show();
                } else {
                    int serverCode = error.networkResponse.statusCode;
                    // Error: El servidor ha dado una respuesta de error
                    Toast.makeText(layout.getContext(), "Server response " + serverCode, Toast.LENGTH_SHORT).show();

                    // La siguiente variable contendrá el código HTTP,
                    // por ejemplo 405, 500,...
                }
            }
        });
        // Agregar la solicitud a la cola de Volley para su procesamiento.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.addmovie_review_fragment, container, false);
        Button send = layout.findViewById(R.id.send);
        send.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendReview(layout);
            }
        });
        return layout;
    }
}
