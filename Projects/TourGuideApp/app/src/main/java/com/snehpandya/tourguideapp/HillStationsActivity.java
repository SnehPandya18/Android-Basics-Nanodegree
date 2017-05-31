package com.snehpandya.tourguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class HillStationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.string.leh, R.string.jammu_kashmir, R.drawable.leh));
        words.add(new Word(R.string.manali, R.string.himachal_pradesh, R.drawable.manali));
        words.add(new Word(R.string.ooty, R.string.tamil_nadu, R.drawable.ooty));
        words.add(new Word(R.string.lavasa, R.string.maharashtra, R.drawable.lavasa));
        words.add(new Word(R.string.darjeeling, R.string.west_bengal, R.drawable.darjeeling));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_hillstations);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}