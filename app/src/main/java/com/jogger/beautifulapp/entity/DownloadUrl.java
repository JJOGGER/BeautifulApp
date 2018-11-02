package com.jogger.beautifulapp.entity;


import java.io.Serializable;

public class DownloadUrl implements Serializable{
    private long id;
    private String url;
    private String name;
    private String channel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "DownloadUrl{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }
}
