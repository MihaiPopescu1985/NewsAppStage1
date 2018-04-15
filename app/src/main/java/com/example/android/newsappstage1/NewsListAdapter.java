package com.example.android.newsappstage1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Mihai on 12.04.2018.
 */

public class NewsListAdapter extends ArrayAdapter<News> {

    public NewsListAdapter(@NonNull Context context, int resource, ArrayList<News> newsList) {
        super(context, resource, newsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        News news = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list, null);
        }
        TextView textView = convertView.findViewById(R.id.news_section_text_view);
        textView.setText(news.getNewsSectionName());

        textView = convertView.findViewById(R.id.news_title_text_view);
        textView.setText(news.getNewsTitle());

        textView = convertView.findViewById(R.id.news_date_text_view);
        textView.setText(news.getNewsPublicationDate());

        return convertView;
    }
}
