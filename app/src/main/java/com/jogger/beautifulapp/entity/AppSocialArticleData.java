package com.jogger.beautifulapp.entity;

import java.util.List;

/**
 * Created by Jogger on 2018/6/17.应用排行
 */

public class AppSocialArticleData {
    private int has_next;
    private List<SocialArticle> apps;

    public int getHas_next() {
        return has_next;
    }

    public void setHas_next(int has_next) {
        this.has_next = has_next;
    }

    public List<SocialArticle> getApps() {
        return apps;
    }

    public void setApps(List<SocialArticle> apps) {
        this.apps = apps;
    }

    @Override
    public String toString() {
        return "AppSocialArticleData{" +
                "has_next=" + has_next +
                ", apps=" + apps +
                '}';
    }
}
