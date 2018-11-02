package com.jogger.beautifulapp.entity;

import java.io.Serializable;
import java.util.List;


public class TopApp implements Serializable{
    private String package_name;
    private String title;
    private String icon_url;
    private int min_sdk_version;
    private String sub_title;
    private String download_url;
    private int is_app;
    private String type;
    private int id;
    private List<DownloadMethod> other_download_url;
    private boolean show;
    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public int getMin_sdk_version() {
        return min_sdk_version;
    }

    public void setMin_sdk_version(int min_sdk_version) {
        this.min_sdk_version = min_sdk_version;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public int getIs_app() {
        return is_app;
    }

    public void setIs_app(int is_app) {
        this.is_app = is_app;
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

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public List<DownloadMethod> getOther_download_url() {
        return other_download_url;
    }

    public void setOther_download_url(List<DownloadMethod> other_download_url) {
        this.other_download_url = other_download_url;
    }

    @Override
    public String toString() {
        return "TopApp{" +
                "package_name='" + package_name + '\'' +
                ", title='" + title + '\'' +
                ", icon_url='" + icon_url + '\'' +
                ", min_sdk_version=" + min_sdk_version +
                ", sub_title='" + sub_title + '\'' +
                ", download_url='" + download_url + '\'' +
                ", is_app=" + is_app +
                ", type='" + type + '\'' +
                ", id=" + id +
                ", other_download_url=" + other_download_url +
                '}';
    }
}
