package com.example.frmnewsapplication;

public class NewsType {
    private int newsTypeId;
    private String newsTypeName;
    private String newsTypeDescription;

    public int getNewsTypeId()
    {
        return this.newsTypeId;
    }

    public void  setNewsTypeId(int value)
    {
        this.newsTypeId=value;
    }

    public String getNewsTypeName()
    {
        return this.newsTypeName;
    }

    public void setNewsTypeName(String value)
    {
        this.newsTypeName=value;
    }

    public  String getNewsTypeDescription()
    {
        return this.newsTypeDescription;
    }

    public void setNewsTypeDescription(String value)
    {
        this.newsTypeDescription=value;
    }
}
