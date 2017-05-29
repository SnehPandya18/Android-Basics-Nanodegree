package com.snehpandya.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ArtistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        Button playing = (Button) findViewById(R.id.playing);
        playing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ArtistsActivity.this, PlayingActivity.class);
                startActivity(i);
            }
        });


        TextView artist1 = (TextView) findViewById(R.id.artist1);
        artist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ArtistsActivity.this, PlayingActivity.class);
                startActivity(a);
            }
        });

        TextView artist2 = (TextView) findViewById(R.id.artist2);
        artist2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(ArtistsActivity.this, PlayingActivity.class);
                startActivity(b);
            }
        });

        TextView artist3 = (TextView) findViewById(R.id.artist3);
        artist3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(ArtistsActivity.this, PlayingActivity.class);
                startActivity(c);
            }
        });
    }
}