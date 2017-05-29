package com.snehpandya.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Button playing = (Button) findViewById(R.id.playing);
        playing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FavoritesActivity.this, PlayingActivity.class);
                startActivity(i);
            }
        });


        TextView fav1 = (TextView) findViewById(R.id.song1);
        fav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(FavoritesActivity.this, PlayingActivity.class);
                startActivity(a);
            }
        });

        TextView fav2 = (TextView) findViewById(R.id.song2);
        fav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(FavoritesActivity.this, PlayingActivity.class);
                startActivity(b);
            }
        });
    }
}