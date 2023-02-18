package com.example.androidfinalproject.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfinalproject.Classes.Song;
import com.example.androidfinalproject.R;
import com.example.androidfinalproject.Utils.MyStringUtils;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Adapter_Song extends RecyclerView.Adapter<Adapter_Song.SongViewHolder> {

    private Context context;
    private ArrayList<Song> songs;

    public Adapter_Song(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    public ArrayList<Song> updateList(ArrayList<Song> songs){
        this.songs = songs;
        return songs;
    }

    @Override
    public int getItemCount() {
        return songs == null ? 0 : songs.size();
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_song, parent, false);
        SongViewHolder mySongViewHolder = new SongViewHolder(view);
        return mySongViewHolder;
    }

    @Override
    public void onBindViewHolder(final SongViewHolder holder, final int position) {
        Log.d("pttt", "Pos= " + position);
        Song song = songs.get(position);
        int temp = position;

        holder.song_LBL_name.setText(song.getName());
        holder.song_LBL_nameauthor.setText(song.getNameAuthor());
        holder.song_LBL_duration.setText(MyStringUtils.getTimeBySeconds(song.getDuration()));
        if(song.getImg() != 0) {
            holder.song_IMG_image.setImageResource(song.getImg());
        }


        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySong.getInstance().reset();
                MySong.currentIndex = temp;
                Intent intent = new Intent(context, SongActivity.class);
                intent.putExtra("LIST", songs);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });*/

        holder.song_IMG_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.setFavorite(true);
            }
        });

        if (song.isFavorite()) {
            holder.song_IMG_star.setVisibility(View.VISIBLE);
        } else {
            holder.song_IMG_star.setVisibility(View.INVISIBLE);
        }
    }

    class SongViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView song_LBL_name;
        private MaterialTextView song_LBL_nameauthor;
        private MaterialTextView song_LBL_duration;

        private AppCompatImageView song_IMG_image;
        private AppCompatImageView song_IMG_star;
        private AppCompatImageView song_IMG_heart;


        public SongViewHolder(View itemView) {
            super(itemView);
            song_LBL_name = itemView.findViewById(R.id.song_LBL_name);
            song_IMG_image = itemView.findViewById(R.id.song_IMG_image);
            song_LBL_nameauthor = itemView.findViewById(R.id.song_LBL_nameAuthor);
            song_LBL_duration = itemView.findViewById(R.id.song_LBL_duration);
            song_IMG_star = itemView.findViewById(R.id.song_IMG_star);
            song_IMG_heart = itemView.findViewById(R.id.song_IMG_heart);
        }

    }
}

