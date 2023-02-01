package com.example.androidfinalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.androidfinalproject.Classes.MySong;
import com.example.androidfinalproject.Classes.Song;
import com.example.androidfinalproject.R;
import com.example.androidfinalproject.Utils.MyStringUtils;

import java.io.IOException;
import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {

    TextView song_TXT_title;
    TextView mysong_TXT_time;
    TextView mysong_TXT_nowtime;
    SeekBar mysong_BAR_seekbar;
    ImageView mysong_IMG_img;
    ImageView  mysong_IMG_previoussong;
    ImageView mysong_IMG_nextsong;
    ImageView mysong_IMG_pause;
    ArrayList<Song> songs;
    Song currentSong;
    MediaPlayer mediaPlayer = MySong.getInstance();
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        findViews();

        song_TXT_title.setSelected(true);
        songs = (ArrayList<Song>) getIntent().getSerializableExtra("LIST");

        setResourcesWithMusic();

        SongActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    mysong_BAR_seekbar.setProgress(mediaPlayer.getCurrentPosition());
                    mysong_TXT_nowtime.setText(MyStringUtils.getTimeBySeconds(mediaPlayer.getCurrentPosition())+"");

                    if(mediaPlayer.isPlaying()){
                        mysong_IMG_pause.setImageResource(R.drawable.ic_pause);
                        mysong_IMG_img.setRotation(x++);
                    }else{
                        mysong_IMG_pause.setImageResource(R.drawable.ic_play);
                        mysong_IMG_img.setRotation(0);
                    }

                }
                new Handler().postDelayed(this,100);
            }
        });

        mysong_BAR_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null && fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void findViews() {
        song_TXT_title = findViewById(R.id.song_TXT_title);
        mysong_TXT_time = findViewById(R.id.mysong_TXT_time);
        mysong_TXT_nowtime = findViewById(R.id.mysong_TXT_nowtime);
        mysong_BAR_seekbar = findViewById(R.id.mysong_BAR_seekbar);
        mysong_IMG_img = findViewById(R.id.mysong_IMG_img);
        mysong_IMG_previoussong = findViewById(R.id.mysong_IMG_previoussong);
        mysong_IMG_nextsong = findViewById(R.id.mysong_IMG_nextsong);
        mysong_IMG_pause = findViewById(R.id.mysong_IMG_pause);
    }

    void setResourcesWithMusic(){
        currentSong = songs.get(MySong.currentIndex);

        song_TXT_title.setText(currentSong.getName() + "/n" + currentSong.getNameAuthor());

        mysong_TXT_time.setText(MyStringUtils.getTimeBySeconds(currentSong.getDuration()));

        mysong_IMG_pause.setOnClickListener(v-> pausePlay());
        mysong_IMG_nextsong.setOnClickListener(v-> playNextSong());
        mysong_IMG_previoussong.setOnClickListener(v-> playPreviousSong());

        playMusic();
    }


    private void playMusic(){

        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentSong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            mysong_BAR_seekbar.setProgress(0);
            mysong_BAR_seekbar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playNextSong(){

        if(MySong.currentIndex== songs.size()-1)
            return;
        MySong.currentIndex +=1;
        mediaPlayer.reset();
        setResourcesWithMusic();

    }

    private void playPreviousSong(){
        if(MySong.currentIndex== 0)
            return;
        MySong.currentIndex -=1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    private void pausePlay(){
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();
    }
}