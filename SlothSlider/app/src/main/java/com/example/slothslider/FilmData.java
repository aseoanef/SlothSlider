package com.example.slothslider;

import org.json.JSONException;
import org.json.JSONObject;

public class FilmData {

    private String name;
    private String description;
    private String image_url;
    private String fecha;
    private Integer puntuacion;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getFecha() {
        return fecha;
    }
    public Integer getPuntuacion() {
        return puntuacion;
    }

    // Constructor que recibe los datos directamente
    public FilmData(String name, String description, String image_url, String fecha, Integer puntuacion) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.fecha = fecha;
        this.puntuacion = puntuacion;
    }

    // Constructor que recibe un objeto JSON y extrae los datos
    public FilmData(JSONObject json) {
        try {
            this.name = json.getString("name");
            this.description = json.getString("description");
            this.image_url = json.getString("image_url");
            this.fecha = json.getString("fecha");
            this.puntuacion = json.getInt("puntuacion");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
