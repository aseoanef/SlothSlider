package com.example.slothslider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class AjustesContentFragment extends Fragment  {
    private static final String TEXT_ID = "text_id";
    private Button idioma;
    private Button politica;
    private Button ayuda;
    private Button privacidad;
    private Integer idweb;




    public static AjustesContentFragment newInstance(@StringRes int textId) {
        AjustesContentFragment frag = new AjustesContentFragment();


        Bundle args = new Bundle();
        args.putInt(TEXT_ID, textId);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.ajustes_fragment, container, false);
        idioma=layout.findViewById(R.id.idioma);
        politica=layout.findViewById(R.id.politica);
        ayuda=layout.findViewById(R.id.ayuda);
        privacidad=layout.findViewById(R.id.privacidad);


        idioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Español", Toast.LENGTH_LONG).show();
            }
        });

        politica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar WebViewActivity
                Intent myIntent = new Intent(getActivity(), WebViewActivity.class);

                // Establecer el valor de "ID-WEB" en el Intent
                idweb = 1;
                myIntent.putExtra("ID-WEB", idweb);

                // Iniciar la actividad usando el Intent
                getActivity().startActivity(myIntent);
            }
        });

        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar WebViewActivity
                Intent myIntent = new Intent(getActivity(), WebViewActivity.class);

                // Establecer el valor de "ID-WEB" en el Intent
                idweb = 2;
                myIntent.putExtra("ID-WEB", idweb);

                // Iniciar la actividad usando el Intent
                getActivity().startActivity(myIntent);
            }
        });
        privacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar WebViewActivity
                Intent myIntent = new Intent(getActivity(), WebViewActivity.class);

                // Establecer el valor de "ID-WEB" en el Intent
                idweb = 3;
                myIntent.putExtra("ID-WEB", idweb);

                // Iniciar la actividad usando el Intent
                getActivity().startActivity(myIntent);
            }
        });

        if (getArguments() != null) {
            String text = getString(getArguments().getInt(TEXT_ID));
            ((TextView) layout.findViewById(R.id.header_title)).setText(text);
        } else {
            throw new IllegalArgumentException("Argument " + TEXT_ID + " is mandatory");
        }

        return layout;
    }
}




