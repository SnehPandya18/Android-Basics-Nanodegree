package com.snehpandya.tourguideapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView attractions = (TextView) findViewById(R.id.attractions);
        attractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent attractionsIntent = new Intent(MainActivity.this, AttractionsActivity.class);
                startActivity(attractionsIntent);
            }
        });

        TextView cities = (TextView) findViewById(R.id.cities);
        cities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent citiesIntent = new Intent(MainActivity.this, CitiesActivity.class);
                startActivity(citiesIntent);
            }
        });

        TextView hillstations = (TextView) findViewById(R.id.hillstations);
        hillstations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hillstationsIntent = new Intent(MainActivity.this, HillStationsActivity.class);
                startActivity(hillstationsIntent);
            }
        });

        TextView hotels = (TextView) findViewById(R.id.hotels);
        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hotelsIntent = new Intent(MainActivity.this, HotelsActivity.class);
                startActivity(hotelsIntent);
            }
        });
    }
}