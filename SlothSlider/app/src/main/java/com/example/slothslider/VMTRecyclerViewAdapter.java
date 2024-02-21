package com.example.slothslider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VMTRecyclerViewAdapter extends RecyclerView.Adapter<VMTViewHolder> {

    private List<ShowLaterMovie> films;
    private Activity activity;

    // Constructor que recibe la lista de datos y la actividad asociada.
    public VMTRecyclerViewAdapter(List<ShowLaterMovie> dataSet, Activity activity) {
        this.films = dataSet;
        this.activity = activity;
    }

    @NonNull
    @Override
    // Método llamado para crear una nueva instancia de FilmViewHolder
    public VMTViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar la vista del diseño de la celda y crear un nuevo FilmViewHolder con esta.
        View filmView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vmt_recycler_cell, parent, false);
        return new VMTViewHolder(filmView);
    }

    @Override
    // Método llamado para actualizar el contenido de un FilmViewHolder específico.
    public void onBindViewHolder(@NonNull VMTViewHolder holder, int position) {

        ShowLaterMovie dataForThisCell = films.get(position);
        holder.showData(dataForThisCell);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("name", dataForThisCell.getName());
                intent.putExtra("description", dataForThisCell.getDescription());
                intent.putExtra("image_url", dataForThisCell.getImage_url());

                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return films.size();
    }
}
