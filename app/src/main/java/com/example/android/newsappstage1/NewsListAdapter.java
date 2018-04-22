package com.example.android.newsappstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

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
            convertView = Objects.requireNonNull(inflater).inflate(R.layout.item_list, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.news_section_text_view);
        textView.setText(Objects.requireNonNull(news).getNewsSectionName());

        textView = convertView.findViewById(R.id.news_title_text_view);
        textView.setText(news.getNewsTitle());

        textView = convertView.findViewById(R.id.news_author_text_view);
        textView.setText(news.getNewsAuthor());

        textView = convertView.findViewById(R.id.news_date_text_view);
        textView.setText(news.getNewsPublicationDate());

        return convertView;
    }
}
