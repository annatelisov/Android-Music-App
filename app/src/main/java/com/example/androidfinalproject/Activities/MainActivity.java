package com.example.androidfinalproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidfinalproject.Adapters.Adapter_Song;
import com.example.androidfinalproject.Classes.User;
import com.example.androidfinalproject.DataManage.DataManager;
import com.example.androidfinalproject.R;
import com.example.androidfinalproject.Classes.Song;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase db;
    private RecyclerView main_LST_songs;
    private TextView main_TXT_nosongs;
    private AppCompatButton main_BTN_gotouserpage;
    private AppCompatButton main_BTN_addsong;
    private LinearLayoutCompat main_LINEAR_addsongwindow;
    private EditText main_TXT_addname;
    private EditText main_TXT_addauthorname;
    private EditText main_TXT_addduration;
    private EditText main_TXT_addpath;
    private AppCompatButton main_BTN_add;
    private AppCompatImageView main_IMG_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        findViews();

        ArrayList<Song> songs = DataManager.getSongs();
        Adapter_Song adapter_song = new Adapter_Song(this, songs);
        main_LST_songs.setLayoutManager(new LinearLayoutManager(this));
        main_LST_songs.setAdapter(adapter_song);

                checkView(adapter_song);
        for(int i = 0; i < adapter_song.getItemCount(); i++){
            saveSongs(songs.get(i));
        }

        main_BTN_gotouserpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserActivity.class));
                finish();
            }
        });

        main_BTN_addsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_LST_songs.setVisibility(View.INVISIBLE);
                main_LINEAR_addsongwindow.setVisibility(View.VISIBLE);
                main_IMG_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        main_LINEAR_addsongwindow.setVisibility(View.INVISIBLE);
                        main_LST_songs.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        main_BTN_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSong(songs);
                main_LINEAR_addsongwindow.setVisibility(View.INVISIBLE);
                main_LST_songs.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    protected void saveSongs(Song song){
        db.getReference("Songs").child(song.getName() + " " + song.getNameAuthor()).setValue(song);
    }

    protected void addSong(ArrayList<Song> songs){
        String name = main_TXT_addname.getText().toString();
        String authorname = main_TXT_addauthorname.getText().toString();
        String strDuration = main_TXT_addduration.getText().toString();
        String path = main_TXT_addpath.getText().toString();
        int duration = Integer.parseInt(strDuration);
        Song newSong = DataManager.newSong(name, authorname, duration, path);
        newSong.setFavorite(true);
        saveSongs(newSong);
        DataManager.addToSongs(songs, newSong);
    }

    private void findViews(){
        main_LST_songs = findViewById(R.id.main_LST_songs);
        main_TXT_nosongs = findViewById(R.id.main_TXT_nosongs);
        main_BTN_gotouserpage = findViewById(R.id.main_BTN_gotouserpage);
        main_BTN_addsong = findViewById(R.id.main_BTN_addsong);
        main_LINEAR_addsongwindow = findViewById(R.id.main_LINEAR_addsongwindow);
        main_TXT_addname = findViewById(R.id.main_TXT_addname);
        main_TXT_addauthorname = findViewById(R.id.main_TXT_addauthorname);
        main_TXT_addduration = findViewById(R.id.main_TXT_addduration);
        main_TXT_addpath = findViewById(R.id.main_TXT_addpath);
        main_BTN_add = findViewById(R.id.main_BTN_add);
        main_IMG_cancel = findViewById(R.id.main_IMG_cancel);
    }

    private void checkView(Adapter_Song adapter_song){
        if(adapter_song.getItemCount() == 0){
            main_TXT_nosongs.setVisibility(View.VISIBLE);
        }else{
            main_TXT_nosongs.setVisibility(View.INVISIBLE);
        }
    }

}