package com.jogger.beautifulapp.entity;

/**
 * Created by jogger on 2018/10/30.
 */
public class UserHomeInfo {
    private int app_counts;
    private int collect_counts;
    private int up_counts;
    private int user_id;

    public int getApp_counts() {
        return app_counts;
    }

    public void setApp_counts(int app_counts) {
        this.app_counts = app_counts;
    }

    public int getCollect_counts() {
        return collect_counts;
    }

    public void setCollect_counts(int collect_counts) {
        this.collect_counts = collect_counts;
    }

    public int getUp_counts() {
        return up_counts;
    }

    public void setUp_counts(int up_counts) {
        this.up_counts = up_counts;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserHomeInfo{" +
                "app_counts=" + app_counts +
                ", collect_counts=" + collect_counts +
                ", up_counts=" + up_counts +
                ", user_id=" + user_id +
                '}';
    }
}
