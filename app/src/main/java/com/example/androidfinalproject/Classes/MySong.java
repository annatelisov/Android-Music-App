package com.example.androidfinalproject.Classes;

import android.media.MediaPlayer;

import com.example.androidfinalproject.Classes.Song;

public class MySong {
    static MediaPlayer instance;

    public static MediaPlayer getInstance(){
        if(instance == null){
            instance =  new MediaPlayer();
        }
        return instance;
    }

    public static int currentIndex = -1;
}
