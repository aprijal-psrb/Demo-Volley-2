package com.asa.demovolley2;

/**
 * Created by APRIJAL_PASARIBU on 16/04/2015.
 */
public class ImageRecord {
    private String url;
    private String title;

    public ImageRecord(String url, String title){
        this.url = url;
        this.title = title;
    }
    public String getUrl(){
        return this.url;
    }
    public String getTitle(){
        return this.title;
    }
}
