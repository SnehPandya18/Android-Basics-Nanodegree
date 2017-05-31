package com.snehpandya.tourguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class HotelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.string.umaid_bhawan, R.string.rajasthan, R.drawable.ubp));
        words.add(new Word(R.string.taj_lake, R.string.rajasthan, R.drawable.tlp));
        words.add(new Word(R.string.oberoi_rajvilas, R.string.rajasthan, R.drawable.orv));
        words.add(new Word(R.string.taj_mahal_palace, R.string.maharashtra, R.drawable.tmp));
        words.add(new Word(R.string.bella_vista, R.string.maharashtra, R.drawable.bvr));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_hotels);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}