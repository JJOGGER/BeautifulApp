package com.jogger.beautifulapp.entity;

import java.util.List;

/**
 * Created by Jogger on 2018/6/9.
 */

public class AppData {
    private int has_next;
    List<AppInfo> apps;

    public int getHas_next() {
        return has_next;
    }

    public void setHas_next(int has_next) {
        this.has_next = has_next;
    }

    public List<AppInfo> getApps() {
        return apps;
    }

    public void setApps(List<AppInfo> apps) {
        this.apps = apps;
    }

    @Override
    public String toString() {
        return "AppData{" +
                "has_next=" + has_next +
                ", apps=" + apps +
                '}';
    }
}
