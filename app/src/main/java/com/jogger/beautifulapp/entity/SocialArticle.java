package com.jogger.beautifulapp.entity;

public class SocialArticle {
    private int article_id;
    private int article_type;
    private String icon_image;
    private String sub_title;
    private String title;

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getArticle_type() {
        return article_type;
    }

    public void setArticle_type(int article_type) {
        this.article_type = article_type;
    }

    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SocialArticle{" +
                "article_id=" + article_id +
                ", article_type=" + article_type +
                ", icon_image='" + icon_image + '\'' +
                ", sub_title='" + sub_title + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
