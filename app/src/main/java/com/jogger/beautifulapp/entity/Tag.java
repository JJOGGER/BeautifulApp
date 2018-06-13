package com.jogger.beautifulapp.entity;

import java.io.Serializable;

/**
 * Created by Jogger on 2018/6/13.
 */

public class Tag implements Serializable{
    private String type;
    private int id;
    private String title;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "Tag{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
