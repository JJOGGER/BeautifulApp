package com.jogger.beautifulapp.entity;


public class Album {
    private String cover_image;
    private String digest;
    private int id;
    private String rgb;
    private String title;

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Album{" +
                "cover_image='" + cover_image + '\'' +
                ", digest='" + digest + '\'' +
                ", id=" + id +
                ", rgb='" + rgb + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
