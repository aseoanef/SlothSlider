package com.example.slothslider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView nombre;
    private ImageView film;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Aquí puedes agregar el código necesario para inicializar y mostrar los detalles de la película en la pantalla de detalles.
        // Por ejemplo, puedes obtener datos del Intent que inició esta actividad.
        Intent intent = getIntent();

        // Cambiar el nombre de la variable local para evitar conflictos
        String name = intent.getStringExtra("name");
        String filmDescription = intent.getStringExtra("description");
        String url = intent.getStringExtra("image_url");

        // Cambiar el nombre del TextView de la clase para evitar conflictos
        nombre = findViewById(R.id.name);
        film = findViewById(R.id.imagen);
        description = findViewById(R.id.descrip_film);

        // Actualizar los TextView con los datos correctos
        nombre.setText(name);
        Util.downloadBitmapToImageView(url, this.film);
        description.setText(filmDescription);
    }
}
