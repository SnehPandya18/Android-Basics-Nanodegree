package com.snehpandya.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlbumsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        Button playing = (Button) findViewById(R.id.playing);
        playing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlbumsActivity.this, PlayingActivity.class);
                startActivity(i);
            }
        });

        TextView album1 = (TextView) findViewById(R.id.album1);
        album1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(AlbumsActivity.this, PlayingActivity.class);
                startActivity(a);
            }
        });

        TextView album2 = (TextView) findViewById(R.id.album2);
        album2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(AlbumsActivity.this, PlayingActivity.class);
                startActivity(b);
            }
        });

        TextView album3 = (TextView) findViewById(R.id.album3);
        album3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(AlbumsActivity.this, PlayingActivity.class);
                startActivity(c);
            }
        });

        TextView album4 = (TextView) findViewById(R.id.album4);
        album4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(AlbumsActivity.this, PlayingActivity.class);
                startActivity(d);
            }
        });

        TextView album5 = (TextView) findViewById(R.id.album5);
        album5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(AlbumsActivity.this, PlayingActivity.class);
                startActivity(e);
            }
        });
    }
}