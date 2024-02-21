package com.example.slothslider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListasRecyclerViewAdapter extends RecyclerView.Adapter<ListasViewHolder> {

    private List<Listas> films;
    private Activity activity;

    // Constructor que recibe la lista de datos y la actividad asociada.
    public ListasRecyclerViewAdapter(List<Listas> dataSet, Activity activity) {
        this.films = dataSet;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ListasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar la vista del diseño de la celda y crear un nuevo FilmViewHolder con esta.
        View filmView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listas_recycler_cell, parent, false);
        return new ListasViewHolder(filmView);
    }

    @Override
    // Método llamado para actualizar el contenido de un FilmViewHolder específico.
    public void onBindViewHolder(@NonNull ListasViewHolder holder, int position) {

        Listas dataForThisCell = films.get(position);
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
