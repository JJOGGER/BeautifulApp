package com.jogger.beautifulapp.entity;


import java.io.Serializable;
import java.util.List;

public class Category implements Serializable{
    private String icon_url;
    private List<TopApp> top_apps;
    private int id;
    private String title;

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public List<TopApp> getTop_apps() {
        return top_apps;
    }

    public void setTop_apps(List<TopApp> top_apps) {
        this.top_apps = top_apps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "AppCategoryData{" +
                "icon_url='" + icon_url + '\'' +
                ", top_apps=" + top_apps +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
