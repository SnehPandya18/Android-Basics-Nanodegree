package com.snehpandya.tourguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class AttractionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.string.lakshmivilas_palace, R.string.gujarat, R.drawable.lvp));
        words.add(new Word(R.string.gateway_of_india, R.string.maharashtra, R.drawable.gwi));
        words.add(new Word(R.string.ajanta_caves, R.string.maharashtra, R.drawable.ajanta));
        words.add(new Word(R.string.sun_temple, R.string.odisha, R.drawable.suntemple));
        words.add(new Word(R.string.lotus_temple, R.string.delhi, R.drawable.lotustemple));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_attractions);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}