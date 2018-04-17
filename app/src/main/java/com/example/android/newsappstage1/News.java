package com.example.android.newsappstage1;

/**
 * Created by Mihai on 12.04.2018.
 */

public class News {

    private String mSectionName;
    private String mPublicationDate;
    private String mTitle;
    private String mUrl;
    private String mAuthor;

    //Constructor
    public News(String sectionName,
                String publicationDate,
                String title,
                String url,
                String author) {

        mSectionName = sectionName;
        mTitle = title;
        mUrl = url;

        //Sets only the first 10 letters of the date
        mPublicationDate = publicationDate.substring(0, 10);

        mAuthor = author;
    }

    //Getter methods
    public String getNewsSectionName() {
        return mSectionName;
    }

    public String getNewsPublicationDate() {
        return mPublicationDate;
    }

    public String getNewsTitle() {
        return mTitle;
    }

    public String getNewsUrl() {
        return mUrl;
    }

    public String getNewsAuthor() {
        return mAuthor;
    }
}
