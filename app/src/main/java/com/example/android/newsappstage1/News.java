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
        mPublicationDate = publicationDate.substring(0, 10);
        mTitle = title;
        mUrl = url;
    }

    public String getNewsSectionName() {return mSectionName;}
    public String getNewsPublicationDate() {return mPublicationDate;}
    public String getNewsTitle() {return mTitle;}
    public String getNewsUrl() {return mUrl;}
}
