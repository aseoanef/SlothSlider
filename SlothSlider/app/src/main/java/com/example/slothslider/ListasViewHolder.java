package com.example.slothslider;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListasViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private ImageView film;

    public ListasViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.text_view_film);
        film = itemView.findViewById(R.id.image_view_film);

    }

    public void showData(Listas filmData) {
        name.setText(filmData.getName());
        Util.downloadBitmapToImageView(filmData.getImage_url(), this.film);

    }
}
