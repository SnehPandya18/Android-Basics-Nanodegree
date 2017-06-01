package com.snehpandya.newsfeed;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public CategoryAdapter(MainActivity mainActivity, FragmentManager fm) {
        super(fm);
        mContext = mainActivity;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TopStories();
        } else if (position == 1) {
            return new Society();
        } else {
            return new Politics();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.top_stories);
        } else if (position == 1) {
            return mContext.getString(R.string.society);
        } else {
            return mContext.getString(R.string.politics);
        }
    }
}