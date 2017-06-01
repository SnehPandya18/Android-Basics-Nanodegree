package com.snehpandya.newsfeed;

import android.graphics.Bitmap;

public class NewsData {
    private String sectionName;
    private String type;
    private String webTitle;
    private String webAbstract;
    private Bitmap webImage;
    private String authorName;
    private String webPublicationDate;
    private String webUrl;

    public NewsData (String name,String itemType, String title, String abstractData, Bitmap image,
                     String aName, String publicationDate, String url ){
        sectionName = name;
        type = itemType;
        webTitle = title;
        webAbstract = abstractData;
        webImage = image;
        authorName = aName;
        webPublicationDate = publicationDate;
        webUrl = url;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getItemType() {
        return type;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebAbstract() {
        return webAbstract;
    }

    public Bitmap getWebImage() {
        return webImage;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getWebUrl() {
        return webUrl;
    }
}