package com.example.androidfinalproject;

import java.util.ArrayList;

public class DataManager {

    public static ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<>();

        songs.add(new Song()
                .setImage("")
                .setName("")
                .setNameAuthor("")
                .setFavorite(true)
        );



        return songs;
    }
}
