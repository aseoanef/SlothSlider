package com.example.slothslider;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FilmViewHolder extends RecyclerView.ViewHolder {

    // Elementos de la vista de la celda
    private TextView name;
    private ImageView film;
    private TextView fecha;
    private TextView puntuacion;

    // Constructor que recibe la vista de la celda e inicializa los atributos con los elementos de la misma.
    public FilmViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.text_view_film);
        film = itemView.findViewById(R.id.image_view_film);
        fecha = itemView.findViewById(R.id.fecha);
        puntuacion = itemView.findViewById(R.id.puntuacion);

    }

    /**
     * Método para mostrar los datos en la vista de la celda.
     *
     * @param filmData Datos de la película a mostrar.
     */
    public void showData(FilmData filmData) {
        name.setText(filmData.getName()); // Establecer el nombre de la película en el TextView
        fecha.setText(filmData.getFecha());
        puntuacion.setText(String.valueOf(filmData.getPuntuacion()));
        Util.downloadBitmapToImageView(filmData.getImage_url(), this.film);


    }
}
