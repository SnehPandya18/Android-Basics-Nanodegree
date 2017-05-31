package com.snehpandya.tourguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class CitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.string.agra, R.string.uttar_pradesh, R.drawable.agra));
        words.add(new Word(R.string.banglore, R.string.karnataka, R.drawable.banglore));
        words.add(new Word(R.string.hyderabad, R.string.telangana, R.drawable.hyderabad));
        words.add(new Word(R.string.jaipur, R.string.rajasthan, R.drawable.jaipur));
        words.add(new Word(R.string.mumbai, R.string.maharashtra, R.drawable.mumbai));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_cities);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}