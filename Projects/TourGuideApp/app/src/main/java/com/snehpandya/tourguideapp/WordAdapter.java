package com.snehpandya.tourguideapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mCityResourceId;

    public WordAdapter(Context context, ArrayList<Word> words, int cityResourceId) {
        super(context, 0, words);
        mCityResourceId = cityResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView cityTextView = (TextView) listItemView.findViewById(R.id.city_textview);
        cityTextView.setText(currentWord.getCity());

        TextView stateTextView = (TextView) listItemView.findViewById(R.id.state_textview);
        stateTextView.setText(currentWord.getState());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentWord.getImageResourceId());

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mCityResourceId);
        textContainer.setBackgroundColor(color);
        return listItemView;
    }
}
