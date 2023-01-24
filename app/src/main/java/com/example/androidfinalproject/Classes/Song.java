package com.example.androidfinalproject.Classes;

public class Song {

    private String name = "";
    private String nameAuthor = "";

    private String path = "";
    private int duration = 0;
    private boolean star = false;
    public Song() {
    }

    public String getName() {
        return name;
    }

    public Song setName(String name) {
        this.name = name;
        return this;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public Song setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Song setPath(String path) {
        this.path = path;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public Song setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public boolean isFavorite() {
        return star;
    }

    public Song setFavorite(boolean star) {
        this.star = star;
        return this;
    }


}
