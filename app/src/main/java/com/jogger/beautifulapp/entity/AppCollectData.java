package com.jogger.beautifulapp.entity;

import java.util.List;

/**
 * Created by Jogger on 2018/6/14.收藏
 */

public class AppCollectData {
    private int has_next;
    private List<Collection> collections;

    public int getHas_next() {
        return has_next;
    }

    public void setHas_next(int has_next) {
        this.has_next = has_next;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    @Override
    public String toString() {
        return "AppCollectData{" +
                "has_next=" + has_next +
                ", collections=" + collections +
                '}';
    }
}
