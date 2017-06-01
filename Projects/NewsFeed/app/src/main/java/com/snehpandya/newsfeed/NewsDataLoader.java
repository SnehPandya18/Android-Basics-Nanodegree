package com.snehpandya.newsfeed;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class NewsDataLoader extends AsyncTaskLoader<List<NewsData>> {
    private static final String LOG_TAG = NewsDataLoader.class.getName();
    private String mUrl;

    public NewsDataLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsData> loadInBackground() {
        if (mUrl == null){
            return null;
        }
        List<NewsData> newsData = QueryResolver.fetchNewsData(mUrl);
        return newsData;
    }
}