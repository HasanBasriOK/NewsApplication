package com.example.frmnewsapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NewsDetailActivity extends AppCompatActivity {

    private int _newsId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Intent intent=getIntent();
        String newsUrl=intent.getStringExtra("ImageUrl");
        String newsHeader=intent.getStringExtra("Header");
        String newsBody=intent.getStringExtra("Body");
        _newsId=intent.getIntExtra("Id",0);

        Viewed();

        TextView textViewHeader= (TextView) findViewById(R.id.textViewDetailHeader);
        TextView textViewBody=(TextView) findViewById(R.id.textViewDetailBody);
        ImageView imageView=(ImageView) findViewById(R.id.imageViewDetailImage);
        Button buttonLike=(Button)findViewById(R.id.buttonLike);
        final Button buttonDislike=(Button)findViewById(R.id.buttonDislike);

        buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Like();
            }
        });

        buttonDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dislike();
            }
        });
        textViewBody.setText(newsBody);
        textViewHeader.setText(newsHeader);

        URL newurl = null;
        try {
            newurl = new URL(newsUrl);
            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
            imageView.setImageBitmap(mIcon_val);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //imageView.setImageURI(Uri.parse(newsUrl));
    }


    private void Dislike()
    {
        URL url;
        HttpURLConnection urlConnection = null;
        String result="";
        try {
            url = new URL(GlobalSettings.ApiUrl+"api/News?newsId="+_newsId+"&increaseVariable=begenmeme");

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        //String message= _newsId+" id'li haber beğenilmedi!";
        Toast.makeText(getApplicationContext(),"Haber Beğenilmedi",Toast.LENGTH_SHORT).show();
    }

    private void Like()
    {
        URL url;
        HttpURLConnection urlConnection = null;
        String result="";
        try {
            url = new URL(GlobalSettings.ApiUrl+"api/News?newsId="+_newsId+"&increaseVariable=begenme");

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        //String message=_newsId+" id'li haber beğenildi";
        Toast.makeText(getApplicationContext(),"Haber Beğenildi",Toast.LENGTH_SHORT).show();
    }

    private void Viewed()
    {
        URL url;
        HttpURLConnection urlConnection = null;
        String result="";
        try {
            url = new URL(GlobalSettings.ApiUrl+"api/News?newsId="+_newsId+"&increaseVariable=goruntulenme");

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
