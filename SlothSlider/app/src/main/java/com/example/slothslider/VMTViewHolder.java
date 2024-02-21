package com.example.slothslider;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VMTViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private ImageView film;

    public VMTViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.text_view_film);
        film = itemView.findViewById(R.id.image_view_film);

    }

    public void showData(ShowLaterMovie filmData) {
        name.setText(filmData.getName());
        Util.downloadBitmapToImageView(filmData.getImage_url(), this.film);

    }
}
