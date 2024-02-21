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

public class FilmRecyclerViewAdapter extends RecyclerView.Adapter<FilmViewHolder> {

    private List<FilmData> films;
    private Activity activity;

    // Constructor que recibe la lista de datos y la actividad asociada.
    public FilmRecyclerViewAdapter(List<FilmData> dataSet, Activity activity) {
        this.films = dataSet;
        this.activity = activity;
    }

    @NonNull
    @Override
    // Método llamado para crear una nueva instancia de FilmViewHolder
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar la vista del diseño de la celda y crear un nuevo FilmViewHolder con esta.
        View filmView = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_recycler_cell, parent, false);
        return new FilmViewHolder(filmView);
    }

    @Override
    // Método llamado para actualizar el contenido de un FilmViewHolder específico.
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        // Obtener los datos correspondientes de la lista y llamar al método showData en el FilmViewHolder.
        FilmData dataForThisCell = films.get(position);
        holder.showData(dataForThisCell);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("name", dataForThisCell.getName());
                    intent.putExtra("description", dataForThisCell.getDescription());
                    intent.putExtra("image_url", dataForThisCell.getImage_url());

                    // Aquí puedes agregar datos adicionales al intent, si es necesario.
                    // Por ejemplo, puedes pasar el ID de la película para recuperar más detalles en la pantalla de detalles.

                    context.startActivity(intent); // Iniciar la actividad de detalles
                }
            });
    }


    @Override
    // Método que devuelve el número total de elementos en la lista de películas.
    public int getItemCount() {
        return films.size();
    }
}
