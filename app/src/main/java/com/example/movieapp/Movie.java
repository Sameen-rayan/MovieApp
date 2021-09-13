package com.example.movieapp;

public class Movie {
    private String title;
    private String poster;

    public String getPhoto() {
        return photo;
    }

    private  String photo;
    public String getReleasedate() {
        return releasedate;
    }

    public String getOverview() {
        return overview;
    }

    public Integer getId() {
        return id;
    }

    private String releasedate;
    private String overview;
    private Double rating;
    private Integer id;

    public Movie(String title,String poster,Double rating,String releasedate,String overview,Integer id,String photo){
        this.title=title;
        this.poster=poster;
        this.rating=rating;
        this.overview=overview;
        this.id=id;
        this.releasedate=releasedate;
        this.photo=photo;
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
