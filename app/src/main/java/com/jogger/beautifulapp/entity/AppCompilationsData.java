package com.jogger.beautifulapp.entity;

import java.util.List;

/**
 * Created by Jogger on 2018/6/14.合辑
 */

public class AppCompilationsData {
    private int has_next;
    private List<Album> albums;

    public int getHas_next() {
        return has_next;
    }

    public void setHas_next(int has_next) {
        this.has_next = has_next;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "AppCompilationsData{" +
                "has_next=" + has_next +
                ", albums=" + albums +
                '}';
    }
}
