package com.example.movieapp;

public class Cast {
    String name;
    String photoPath;

    public Cast(String name, String photoPath) {
        this.name = name;
        this.photoPath = photoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
