package com.snehpandya.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class National extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsData>> {
    String input = "http://content.guardianapis.com/search?q=national&api-key=YOUR-KEY-HERE";
    private static final int NEWS_DATA_LOADER_ID = 9;
    private NewsDataAdapter newsDataAdapter;
    View progressBar;

    public National() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list_item,container,false);
        ListView newsDataListView = (ListView) rootView.findViewById(R.id.list);
        newsDataAdapter = new NewsDataAdapter(getContext(), new ArrayList<NewsData>());
        newsDataListView.setAdapter(newsDataAdapter);
        newsDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsData currentData = newsDataAdapter.getItem(position);
                final String URL = "webUrl";
                final String TITLE = "toolbarTitle";
                String mUrl = currentData.getWebUrl();
                String mTitle = getResources().getString(R.string.national);

                Intent sendIntent = new Intent(getContext(),ItemClickResult.class);
                sendIntent.putExtra(URL,mUrl);
                sendIntent.putExtra(TITLE,mTitle);
                startActivity(sendIntent);
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        progressBar = rootView.findViewById(R.id.loading_indicator);

        if (networkInfo != null && networkInfo.isConnected()) {
            progressBar.setVisibility(View.VISIBLE);
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_DATA_LOADER_ID, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(),"Check Your internet Connection",Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }

    @Override
    public Loader<List<NewsData>> onCreateLoader(int id, Bundle args) {
        return new NewsDataLoader(getContext(),input);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsData>> loader, List<NewsData> newsData) {
        progressBar.setVisibility(View.GONE);
        newsDataAdapter.clear();
        if (newsData != null && !newsData.isEmpty()) {
            newsDataAdapter.addAll(newsData);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsData>> loader) {
        newsDataAdapter.clear();
    }
}