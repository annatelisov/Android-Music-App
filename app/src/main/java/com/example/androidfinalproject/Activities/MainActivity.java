package com.example.androidfinalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.androidfinalproject.Adapters.Adapter_Song;
import com.example.androidfinalproject.DataManage.DataManager;
import com.example.androidfinalproject.R;
import com.example.androidfinalproject.Classes.Song;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private RecyclerView main_LST_songs;
    private TextView main_TXT_nosongs;
    private AppCompatButton main_BTN_gotouserpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        findViews();

        ArrayList<Song> songs = DataManager.getSongs();
        Adapter_Song adapter_song = new Adapter_Song(this, songs);
        main_LST_songs.setLayoutManager(new LinearLayoutManager(this));
        main_LST_songs.setAdapter(adapter_song);

        checkView(adapter_song);

        main_BTN_gotouserpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserActivity.class));
                finish();
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

    private void findViews(){
        main_LST_songs = findViewById(R.id.main_LST_songs);
        main_TXT_nosongs = findViewById(R.id.main_TXT_nosongs);
        main_BTN_gotouserpage = findViewById(R.id.main_BTN_gotouserpage);
    }

    private void checkView(Adapter_Song adapter_song){
        if(adapter_song.getItemCount() == 0){
            main_TXT_nosongs.setVisibility(View.VISIBLE);
        }else{
            main_TXT_nosongs.setVisibility(View.INVISIBLE);
        }
    }

}