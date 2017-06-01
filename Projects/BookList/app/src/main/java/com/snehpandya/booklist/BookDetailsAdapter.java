package com.snehpandya.booklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookDetailsAdapter extends ArrayAdapter<BookDetails> {

    public BookDetailsAdapter(Context context, ArrayList<BookDetails> bookDetails ) {
        super(context, 0 , bookDetails);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_lists, parent, false);
        }

        BookDetails currentItem = getItem(position);
        TextView bookNameView = (TextView) listItemView.findViewById(R.id.bookname);
        bookNameView.setText(currentItem.getBookName());

        TextView authorNameView = (TextView) listItemView.findViewById(R.id.author);
        authorNameView.setText(currentItem.getAuthorName());

        return listItemView;
    }
}