package com.jogger.beautifulapp.entity;

import java.io.Serializable;


public class DownloadMethod implements Serializable{
    private String app_market_name;
    private String app_market_verbose_name;
    private String download_url;

    public String getApp_market_name() {
        return app_market_name;
    }

    public void setApp_market_name(String app_market_name) {
        this.app_market_name = app_market_name;
    }

    public String getApp_market_verbose_name() {
        return app_market_verbose_name;
    }

    public void setApp_market_verbose_name(String app_market_verbose_name) {
        this.app_market_verbose_name = app_market_verbose_name;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    @Override
    public String toString() {
        return "DownloadMethod{" +
                "app_market_name='" + app_market_name + '\'' +
                ", app_market_verbose_name='" + app_market_verbose_name + '\'' +
                ", download_url='" + download_url + '\'' +
                '}';
    }
}
