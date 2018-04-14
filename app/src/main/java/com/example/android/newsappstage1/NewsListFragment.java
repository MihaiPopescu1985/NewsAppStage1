package com.example.android.newsappstage1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsListFragment extends ListFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        News[] newsList = {};
        NewsListAdapter newsListAdapter = new NewsListAdapter(getContext(), R.layout.item_list, newsList);
        setListAdapter(newsListAdapter);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflater.inflate(R.layout.list_fragment, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        setEmptyText("e bai mare");
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
