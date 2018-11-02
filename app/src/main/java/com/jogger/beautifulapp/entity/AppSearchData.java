package com.jogger.beautifulapp.entity;

import java.util.List;

/**
 * Created by jogger on 2018/10/30.
 */
public class AppSearchData {
    private List<TopApp> apps;
    private List<TopApp> articles;

    public List<TopApp> getApps() {
        return apps;
    }

    public void setApps(List<TopApp> apps) {
        this.apps = apps;
    }

    public List<TopApp> getArticles() {
        return articles;
    }

    public void setArticles(List<TopApp> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "AppSearchData{" +
                "apps=" + apps +
                ", articles=" + articles +
                '}';
    }
}
