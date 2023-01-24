package com.example.androidfinalproject.DataManage;

import com.example.androidfinalproject.Classes.Song;

import java.util.ArrayList;

public class DataManager {

    public static Song newSong(String name, String authorname, int duration){
        Song song = new Song()
                .setName(name)
                .setNameAuthor(authorname)
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
                //.setImage("https://m.media-amazon.com/images/I/810eYIM3PZL.__AC_SX300_SY300_QL70_FMwebp_.jpg")
                .setName("Thinking out Loud")
                .setNameAuthor("Ed Sheeran")
                .setDuration(281)
        );

        songs.add(new Song()
                //.setImage("https://m.media-amazon.com/images/I/810eYIM3PZL.__AC_SX300_SY300_QL70_FMwebp_.jpg")
                .setName("Thinking out Loud")
                .setNameAuthor("Ed Sheeran")
                .setDuration(281)
        );

        songs.add(new Song()
                //.setImage("https://upload.wikimedia.org/wikipedia/en/4/43/Photograph_cover.png?20151102161534")
                .setName("Photograph")
                .setNameAuthor("Ed Sheeran")
                .setDuration(265)
        );

        songs.add(new Song()
                //.setImage("https://upload.wikimedia.org/wikipedia/en/8/80/Ed_Sheeran_Perfect_Single_cover.jpg")
                .setName("Perfect")
                .setNameAuthor("Ed Sheeran")
                .setDuration(263)
        );

        songs.add(new Song()
                //.setImage("https://i.pinimg.com/originals/6b/58/40/6b58403180793e7b8da5699428a64dbe.jpg")
                .setName("A Thousand Years")
                .setNameAuthor("Christina Perri")
                .setDuration(328)
        );

        songs.add(new Song()
                //.setImage("https://i0.wp.com/jesusful.com/wp-content/uploads/2022/07/Rihanna-Diamonds-Mp3-Download-Lyrics.jpg?w=768&ssl=1")
                .setName("Someone Like You")
                .setNameAuthor("Adele")
                .setDuration(284)
        );

        songs.add(new Song()
                //.setImage("https://i0.wp.com/jesusful.com/wp-content/uploads/2022/07/Rihanna-Diamonds-Mp3-Download-Lyrics.jpg?w=768&ssl=1")
                .setName("Diamonds")
                .setNameAuthor("Rihanna")
                .setDuration(225)
        );

        return songs;
    }
}
