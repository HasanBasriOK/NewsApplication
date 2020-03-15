package com.example.frmnewsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //List<News> newsList=GetNewsList();
        Intent intent=getIntent();
        String category=intent.getStringExtra("NewsType");
        List<News> newsList = GetNewsListByCategory(category);

        final ListView listView=(ListView)findViewById(R.id.listView);
        final CustomAdapter adapter=new CustomAdapter(newsList,this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news=(News) adapter.getItem(position);
                // Toast.makeText(getApplicationContext(),news.getNewsBody(),Toast.LENGTH_LONG).show();

                Intent intent =new Intent(ListViewActivity.this,NewsDetailActivity.class);
                intent.putExtra("ImageUrl",news.getNewsUrl());
                intent.putExtra("Header",news.getNewsHeader());
                intent.putExtra("Body",news.getNewsBody());
                intent.putExtra("Id",news.getNewsId());

                startActivity(intent);
            }
        });
    }

    public String GetNewsByCategory(String newsType)
    {
        URL url;
        HttpURLConnection urlConnection = null;
        String result="";
        try {
            url = new URL(GlobalSettings.ApiUrl+"api/News?newsTypeName="+newsType);

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();
            result=readStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }


    private List<News> GetNewsListByCategory(String categoryName)
    {
        List<News> newsList=new ArrayList<News>();

        String jsonString=GetNewsByCategory(categoryName);

        try {
            JSONArray jsonArray=new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonobject = jsonArray.getJSONObject(i);

                News newsObject=new News();
                newsObject.setNewsId(jsonobject.getInt("NewsId"));
                newsObject.setNewsUrl(jsonobject.getString("NewsImageUrl"));
                newsObject.setNewsHeader(jsonobject.getString("NewsTitle"));
                newsObject.setNewsBody(jsonobject.getString("NewsContent"));


                SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Date publishDateStr=sf.parse(jsonobject.getString("NewsReleaseDate"));
                newsObject.setPublishDate(publishDateStr.toLocaleString());
                newsList.add(newsObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*News news1=new News();
        news1.setNewsBody("İstanbulu Sel Aldı a Yavrııım");
        news1.setNewsHeader("Yimeden Gidesiceler!!");
        news1.setNewsId(1);
        news1.setNewsUrl("https://i.hizliresim.com/Ln22Rb.jpg");
        newsList.add(news1);*/
        return newsList;
    }

    private List<News> GetNewsList()
    {
        List<News> list=new ArrayList<News>();

        News news1=new News();
        news1.setNewsBody("İstanbulu Sel Aldı a Yavrııım");
        news1.setNewsHeader("Yimeden Gidesiceler!!");
        news1.setNewsId(1);
        news1.setNewsUrl("https://i.hizliresim.com/Ln22Rb.jpg");

        News news2=new News();
        news2.setNewsHeader("Gümüleyesiceler");
        news2.setNewsId(2);
        news2.setNewsUrl("https://i.hizliresim.com/0B88yB.jpg");
        news2.setNewsBody("3 tane serseri çatışırken gümüleyiveeedi");

        News news3=new News();
        news3.setNewsHeader("Boku çıktı");
        news3.setNewsId(3);
        news3.setNewsUrl("https://i.hizliresim.com/nWYq3a.jpg");
        news3.setNewsBody("Uyurken araba tarafından ezilen bok böceğini boku çıktı");

        list.add(news1);
        list.add(news2);
        list.add(news3);
        return list;
    }
}
