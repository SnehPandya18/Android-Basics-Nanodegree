package com.snehpandya.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        Button playing = (Button) findViewById(R.id.playing);
        playing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SongsActivity.this, PlayingActivity.class);
                startActivity(i);
            }
        });

        TextView song1 = (TextView) findViewById(R.id.song1);
        song1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SongsActivity.this, PlayingActivity.class);
                startActivity(a);
            }
        });

        TextView song2 = (TextView) findViewById(R.id.song2);
        song2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(SongsActivity.this, PlayingActivity.class);
                startActivity(b);
            }
        });

        TextView song3 = (TextView) findViewById(R.id.song3);
        song3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(SongsActivity.this, PlayingActivity.class);
                startActivity(c);
            }
        });

        TextView song4 = (TextView) findViewById(R.id.song4);
        song4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(SongsActivity.this, PlayingActivity.class);
                startActivity(d);
            }
        });

        TextView song5 = (TextView) findViewById(R.id.song5);
        song5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(SongsActivity.this, PlayingActivity.class);
                startActivity(e);
            }
        });
    }
}