package com.example.movieapp;

public class Movie {
    private String title,poster;
    private Double rating;
    public Movie(String title,String poster,Double rating){
        this.title=title;
        this.poster=poster;
        this.rating=rating;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public Double getRating() {
        return rating;
    }
}
