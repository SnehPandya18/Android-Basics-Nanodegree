package com.snehpandya.booklist;

import android.graphics.Bitmap;

public class BookDetails {
    private String bookName;
    private String authorName;
    private Bitmap imageResourceUrl;
    private String imageId;

    public BookDetails(String book, String author, Bitmap imageUrl, String image) {
        bookName = book;
        authorName = author;
        imageResourceUrl = imageUrl;
        imageId = image;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Bitmap getImageResourceUrl() {
        return imageResourceUrl;
    }

    public String getImageId(){
        return imageId;
    }
}