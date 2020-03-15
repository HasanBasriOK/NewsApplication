package com.example.frmnewsapplication;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearSmoothScroller;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private List<News> _newsList;
    private LayoutInflater newsInflater;

    public CustomAdapter(List<News> newsList, Activity activity)
    {
        newsInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this._newsList=newsList;
    }

    @Override
    public int getCount() {
        return _newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return _newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  _newsList.get(position).getNewsId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View lineView;

        lineView=newsInflater.inflate(R.layout.custom_layout,null);
        TextView textViewNewsHeader=(TextView)lineView.findViewById(R.id.textViewNewsHeader);
        TextView textViewNewsBody=(TextView)lineView.findViewById(R.id.textViewNewsBody);
        ImageView imageViewUserPicture = (ImageView) lineView.findViewById(R.id.imageViewUserPicture);



        News news=_newsList.get(position);
        textViewNewsBody.setText(news.getPublishDate());
        textViewNewsHeader.setText(news.getNewsHeader());
        imageViewUserPicture.setImageURI(Uri.parse(news.getNewsUrl()));

        return lineView;
    }
}
