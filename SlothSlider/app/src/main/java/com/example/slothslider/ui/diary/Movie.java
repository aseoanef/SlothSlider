package com.example.slothslider.ui.diary;

public class Movie {
    private int imageResId;
    private String title;
    private int rating;

    public Movie(int imageResId, String title, int rating) {
        this.imageResId = imageResId;
        this.title = title;
        this.rating = rating;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public int getRating() {
        return rating;
    }
}
