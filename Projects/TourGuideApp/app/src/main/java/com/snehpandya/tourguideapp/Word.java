package com.snehpandya.tourguideapp;

public class Word {

    private int mCity;
    private int mState;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(int city, int state, int imageResourceId) {
        mCity = city;
        mState = state;
        mImageResourceId = imageResourceId;
    }

    public int getCity() {
        return mCity;
    }

    public int getState() {
        return mState;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }
}