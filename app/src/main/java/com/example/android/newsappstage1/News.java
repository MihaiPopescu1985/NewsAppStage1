package com.example.android.newsappstage1;

/**
 * Created by Mihai on 12.04.2018.
 */

public class News {

    private String mSectionName;
    private String mPublicationDate;
    private String mTitle;
    private String mUrl;

    public News (String sectionName, String publicationDate,
                 String title, String url){

        mSectionName = sectionName;
        mPublicationDate = publicationDate;
        mTitle = title;
        mUrl = url;
    }

    public String getSectionName() {return mSectionName;}
    public String getPublicationDate() {return mPublicationDate;}
    public String getTitle() {return mTitle;}
    public String getUrl() {return mUrl;}
}
