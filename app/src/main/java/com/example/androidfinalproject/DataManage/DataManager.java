package com.example.androidfinalproject.DataManage;

import com.example.androidfinalproject.Classes.Song;
import com.example.androidfinalproject.R;

import java.util.ArrayList;

public class DataManager {

    public static ArrayList<Song> setSongs() {
        ArrayList<Song> songs = new ArrayList<>();

        songs.add(new Song()
                .setName("Thinking out Loud")
                .setNameAuthor("Ed Sheeran")
                .setPath("https://www.youtube.com/watch?v=VDzBQvIUn6o")
                .setImg(R.drawable.thinkingofloud)
                .setDuration(281)
        );

        songs.add(new Song()
                .setName("Photograph")
                .setNameAuthor("Ed Sheeran")
                .setPath("https://www.youtube.com/watch?v=hfukCZC1xQ8")
                .setImg(R.drawable.photograph)
                .setDuration(297)
        );

        songs.add(new Song()
                .setName("Perfect")
                .setNameAuthor("Ed Sheeran")
                .setPath("https://www.youtube.com/watch?v=_x4a3OsvwkY")
                .setImg(R.drawable.perfect)
                .setDuration(263)
        );

        songs.add(new Song()
                .setName("A Thousand Years")
                .setNameAuthor("Christina Perri")
                .setPath("https://www.youtube.com/watch?v=kDKxQiVdy1c")
                .setImg(R.drawable.thousandyears)
                .setDuration(328)
        );

        songs.add(new Song()
                .setName("Someone Like You")
                .setNameAuthor("Adele")
                .setPath("https://www.youtube.com/watch?v=iZqs1NPrs08")
                .setImg(R.drawable.someonelikeyou)
                .setDuration(284)
        );

        songs.add(new Song()
                .setName("Diamonds")
                .setNameAuthor("Rihanna")
                .setPath("https://www.youtube.com/watch?v=lWA2pjMjpBs")
                .setImg(R.drawable.diamonds)
                .setDuration(225)
        );

        return songs;
    }
}
