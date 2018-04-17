package com.example.android.newsappstage1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsListFragment extends ListFragment
        implements LoaderManager.LoaderCallbacks<String> {

    private NewsListAdapter mNewsListAdapter;
    private ArrayList<News> mNewsList = new ArrayList<>();

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //If a news is tapped, then an intent will open the link of the news
        News news = (News) getListView().getItemAtPosition(position);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(news.getNewsUrl())));
    }

    //Fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(0, null, this).forceLoad();

        mNewsListAdapter = new NewsListAdapter(getContext(), R.layout.item_list, mNewsList);
        setListAdapter(mNewsListAdapter);
    }

    //Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflater.inflate(R.layout.list_fragment, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //Fragment
    @Override
    public void onStart() {

        super.onStart();
    }

    //Fragment
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {

        return new NewsLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data.matches(getString(R.string.url_error)) || data.matches(getString(R.string.download_error)))
            setEmptyText(data);
        else
            extractNews(data);
        mNewsListAdapter.addAll(mNewsList);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    private void extractNews(String data) {

        try {
            JSONObject baseJsonObject = new JSONObject(data);
            JSONObject rootJsonObject = baseJsonObject.getJSONObject("response");
            JSONArray newsJsonArray = rootJsonObject.getJSONArray("results");

            for (int i = 0; i < newsJsonArray.length(); i++) {

                JSONObject currentNews = newsJsonArray.getJSONObject(i);

                String sectionName = currentNews.getString("sectionName");
                String webPublicationDate = currentNews.getString("webPublicationDate");
                String webTitle = currentNews.getString("webTitle");
                String webUrl = currentNews.getString("webUrl");
                String webAuthor;

                try {
                    webAuthor = currentNews.getString("webAuthor");
                } catch (JSONException e) {
                    e.printStackTrace();
                    webAuthor = "";
                }

                mNewsList.add(new News(
                        sectionName,
                        webPublicationDate,
                        webTitle,
                        webUrl,
                        webAuthor));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            setEmptyText("Error parsing server response.");
        }
    }
}
