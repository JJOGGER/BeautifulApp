package com.jogger.beautifulapp.entity;


import java.io.Serializable;

public class SameApp implements Serializable {
    private User author;
    private String url;
    private String up_times;
    private String type;
    private int id;
    private String digest;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUp_times() {
        return up_times;
    }

    public void setUp_times(String up_times) {
        this.up_times = up_times;
    }

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

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    @Override
    public String toString() {
        return "SameApp{" +
                "author=" + author +
                ", url='" + url + '\'' +
                ", up_times='" + up_times + '\'' +
                ", type='" + type + '\'' +
                ", id=" + id +
                ", digest='" + digest + '\'' +
                '}';
    }
}
