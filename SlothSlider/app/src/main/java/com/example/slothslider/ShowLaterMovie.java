package com.example.slothslider;

import org.json.JSONException;
import org.json.JSONObject;

public class ShowLaterMovie {

    private String name;
    private String description;
    private String image_url;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }


    public ShowLaterMovie(String name, String description, String image_url) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
    }

    public ShowLaterMovie(JSONObject json) {
        try {
            this.name = json.getString("name");
            this.description = json.getString("description");
            this.image_url = json.getString("image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
