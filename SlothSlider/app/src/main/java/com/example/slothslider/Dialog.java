package com.example.slothslider;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Dialog extends AppCompatDialogFragment {
    private static final String TAG = "DialogFragment";
    private AlertDialog.Builder builder;

    private RequestQueue queue;

    public Dialog() {
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Context context = getContext();
        if (context != null) {
            builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.dialog, null);
            queue = Volley.newRequestQueue(view.getContext());
            builder.setView(view)
                    .setTitle("¿Seguro que desea borrar la cuenta?")
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })

                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent myIntent = new Intent(getActivity(), DeleteUserActivity.class);
                            getActivity().startActivity(myIntent);

                        }

                    });
        } else{
            Log.e(TAG, "El contexto es nulo al intentar crear el AlertDialog.");
        }
        return builder.create();
    }
    }


