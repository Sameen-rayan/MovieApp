package com.example.movieapp;

public class Cast {
    private static String name;
    static String photoPath;

    public Cast(String name,String photoPath)
    {
        this.name=name;
        this.photoPath=photoPath;
    }

    public static String getName() {
        return name;
    }

    public static String getPhotoPath() {
        return photoPath;
    }



}
