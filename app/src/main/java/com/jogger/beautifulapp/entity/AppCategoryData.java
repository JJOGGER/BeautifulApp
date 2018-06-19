package com.jogger.beautifulapp.entity;

import java.util.List;

/**
 * Created by Jogger on 2018/6/17.精选分类
 */

public class AppCategoryData {
    private List<Category> apps;

    public List<Category> getApps() {
        return apps;
    }

    public void setApps(List<Category> apps) {
        this.apps = apps;
    }

    @Override
    public String toString() {
        return "AppCategoryData{" +
                "apps=" + apps +
                '}';
    }
}
