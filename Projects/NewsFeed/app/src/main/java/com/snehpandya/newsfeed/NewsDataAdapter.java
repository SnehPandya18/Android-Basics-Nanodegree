package com.snehpandya.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewsDataAdapter extends ArrayAdapter <NewsData> {
    public NewsDataAdapter(Context context, List<NewsData> newsFeed) {
        super(context, 0, newsFeed);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null){
            listViewItem  = LayoutInflater.from(getContext()).inflate(R.layout.news_item,parent,false);
        }

        NewsData currentItem = getItem(position);

        TextView sectionName = (TextView) listViewItem.findViewById(R.id.section_name_text_view);
        sectionName.setText(currentItem.getSectionName());

        TextView itemType = (TextView) listViewItem.findViewById(R.id.item_type_text_view);
        itemType.setText(currentItem.getItemType());

        TextView webTitle = (TextView) listViewItem.findViewById(R.id.web_title_text_view);
        webTitle.setText(currentItem.getWebTitle());

        TextView webAbstractData = (TextView) listViewItem.findViewById(R.id.web_abstract_text_view);
        webAbstractData.setText(currentItem.getWebAbstract());

        ImageView articleImage = (ImageView)listViewItem.findViewById(R.id.article_image);
        articleImage.setImageBitmap(currentItem.getWebImage());

        TextView authorName = (TextView)listViewItem.findViewById(R.id.author_text_view);
        authorName.setText(currentItem.getAuthorName());

        TextView webPublicationDate = (TextView) listViewItem.findViewById(R.id.web_publication_date_text_view);
        webPublicationDate.setText(currentItem.getWebPublicationDate());

        return listViewItem;
    }
}