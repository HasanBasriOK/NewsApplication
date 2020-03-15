package com.example.frmnewsapplication;

import java.util.Date;

public class News {
    private String newsHeader;
    private String newsBody;
    private String newsUrl;
    private int newsId=0;
    private String publishDate;

    public String getNewsHeader()
    {
        return this.newsHeader;
    }

    public void setNewsHeader(String value)
    {
        this.newsHeader=value;
    }

    public String getNewsBody()
    {
        return this.newsBody;
    }

    public void setNewsBody(String value)
    {
        this.newsBody=value;
    }

    public String getNewsUrl()
    {
        return this.newsUrl;
    }

    public  void setNewsUrl(String value)
    {
        this.newsUrl=value;
    }

    public int getNewsId()
    {
        return this.newsId;
    }

    public void setNewsId(int value)
    {
        this.newsId=value;
    }

    public void setPublishDate(String date)
    {
        this.publishDate=date;
    }

    public String getPublishDate()
    {
        return this.publishDate;
    }
}
