package com.jogger.beautifulapp.entity;

import java.io.Serializable;
import java.util.List;


public class MediaArticle implements Serializable {
    private String title;
    private String sub_title;
    private String icon_image;
    private int article_type;
    private String rgb;
    private int up_times;
    private String cover_image;
    private String recommend_level;
    private int id;
    private String digest;
    private List<String> tags;
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public int getArticle_type() {
        return article_type;
    }

    public void setArticle_type(int article_type) {
        this.article_type = article_type;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public int getUp_times() {
        return up_times;
    }

    public void setUp_times(int up_times) {
        this.up_times = up_times;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getRecommend_level() {
        return recommend_level;
    }

    public void setRecommend_level(String recommend_level) {
        this.recommend_level = recommend_level;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MediaArticle{" +
                "title='" + title + '\'' +
                ", sub_title='" + sub_title + '\'' +
                ", icon_image='" + icon_image + '\'' +
                ", article_type=" + article_type +
                ", rgb='" + rgb + '\'' +
                ", up_times=" + up_times +
                ", cover_image='" + cover_image + '\'' +
                ", recommend_level='" + recommend_level + '\'' +
                ", id=" + id +
                ", digest='" + digest + '\'' +
                ", tags=" + tags +
                '}';
    }

}
