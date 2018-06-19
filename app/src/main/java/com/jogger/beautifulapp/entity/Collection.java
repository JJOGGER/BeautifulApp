package com.jogger.beautifulapp.entity;


public class Collection {
    private String source_msg;
    private String title;
    private int comment_times;
    private String source;
    private int up_times;
    private int down_times;
    private int id;
    private String icon;

    public String getSource_msg() {
        return source_msg;
    }

    public void setSource_msg(String source_msg) {
        this.source_msg = source_msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getComment_times() {
        return comment_times;
    }

    public void setComment_times(int comment_times) {
        this.comment_times = comment_times;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getUp_times() {
        return up_times;
    }

    public void setUp_times(int up_times) {
        this.up_times = up_times;
    }

    public int getDown_times() {
        return down_times;
    }

    public void setDown_times(int down_times) {
        this.down_times = down_times;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "source_msg='" + source_msg + '\'' +
                ", title='" + title + '\'' +
                ", comment_times=" + comment_times +
                ", source='" + source + '\'' +
                ", up_times=" + up_times +
                ", down_times=" + down_times +
                ", id=" + id +
                ", icon='" + icon + '\'' +
                '}';
    }
}
