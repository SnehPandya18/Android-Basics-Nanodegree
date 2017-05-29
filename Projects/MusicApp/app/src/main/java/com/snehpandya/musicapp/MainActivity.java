package com.snehpandya.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playing = (Button) findViewById(R.id.playing);
        playing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PlayingActivity.class);
                startActivity(i);
            }
        });

        TextView songs = (TextView) findViewById(R.id.songs);
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MainActivity.this, SongsActivity.class);
                startActivity(j);
            }
        });

        TextView artists = (TextView) findViewById(R.id.artists);
        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(MainActivity.this, ArtistsActivity.class);
                startActivity(k);
            }
        });

        TextView albums = (TextView) findViewById(R.id.albums);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(MainActivity.this, AlbumsActivity.class);
                startActivity(l);
            }
        });

        TextView favorites = (TextView) findViewById(R.id.favorites);
        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(m);
            }
        });
    }
}