package com.anhdt.mediapicker;

/**
 * Created by admin on 9/8/2017.
 */

public class Image {
    private String id;
    private String url;

    public Image(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "ID= " + id + ", URL= " + url + " \n";
    }
}
