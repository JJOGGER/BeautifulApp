package com.jogger.beautifulapp.entity;

import java.util.List;

/**
 * Created by Jogger on 2018/6/14.周边
 */

public class AppMediaArticleData {
    private int has_next;
    private List<MediaArticle> media_articles;

    public int getHas_next() {
        return has_next;
    }

    public void setHas_next(int has_next) {
        this.has_next = has_next;
    }

    public List<MediaArticle> getMedia_articles() {
        return media_articles;
    }

    public void setMedia_articles(List<MediaArticle> media_articles) {
        this.media_articles = media_articles;
    }

    @Override
    public String toString() {
        return "AppMediaArticleData{" +
                "has_next=" + has_next +
                ", media_articles=" + media_articles +
                '}';
    }
}
