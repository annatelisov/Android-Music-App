package com.example.androidfinalproject.DataManage;

import com.example.androidfinalproject.Classes.Song;

import java.util.ArrayList;

public class DataManager {

    public static Song newSong(String name, String authorname, int duration, String path){
        Song song = new Song()
                .setName(name)
                .setNameAuthor(authorname)
                .setPath(path)
                .setDuration(duration);
        return song;
    }
    public static ArrayList<Song> addToSongs(ArrayList<Song> songs, Song song){
        ArrayList<Song> updatedSongs = songs;
        updatedSongs.add(song);
        return  updatedSongs;
    }
    public static ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<>();

        songs.add(new Song()
                .setName("Thinking out Loud")
                .setNameAuthor("Ed Sheeran")
                .setPath("https://www.youtube.com/watch?v=VDzBQvIUn6o")
                .setDuration(281)
        );

        songs.add(new Song()
                .setName("Photograph")
                .setNameAuthor("Ed Sheeran")
                .setPath("https://www.youtube.com/watch?v=hfukCZC1xQ8")
                .setDuration(297)
        );

        songs.add(new Song()
                .setName("Perfect")
                .setNameAuthor("Ed Sheeran")
                .setPath("https://www.youtube.com/watch?v=_x4a3OsvwkY")
                .setDuration(263)
        );

        songs.add(new Song()
                .setName("A Thousand Years")
                .setNameAuthor("Christina Perri")
                .setPath("https://www.youtube.com/watch?v=kDKxQiVdy1c")
                .setDuration(328)
        );

        songs.add(new Song()
                .setName("Someone Like You")
                .setNameAuthor("Adele")
                .setPath("https://www.youtube.com/watch?v=iZqs1NPrs08")
                .setDuration(284)
        );

        songs.add(new Song()
                .setName("Diamonds")
                .setNameAuthor("Rihanna")
                .setPath("https://www.youtube.com/watch?v=lWA2pjMjpBs")
                .setDuration(225)
        );

        return songs;
    }
}
