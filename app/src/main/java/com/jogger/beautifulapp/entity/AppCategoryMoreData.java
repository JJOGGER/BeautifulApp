package com.jogger.beautifulapp.entity;

import java.util.List;

/**
 * Created by jogger on 2018/11/1.
 */
public class AppCategoryMoreData {
    private int has_next;
    private List<TopApp> apps;

    public int getHas_next() {
        return has_next;
    }

    public void setHas_next(int has_next) {
        this.has_next = has_next;
    }

    public List<TopApp> getApps() {
        return apps;
    }

    public void setApps(List<TopApp> apps) {
        this.apps = apps;
    }

    @Override
    public String toString() {
        return "AppCategoryMoreData{" +
                "has_next=" + has_next +
                ", apps=" + apps +
                '}';
    }
}
