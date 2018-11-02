package com.jogger.beautifulapp.entity;

import java.util.List;

/**
 * Created by jogger on 2018/10/19.
 */
public class DownloadItem {
    private int has_next;
    private List<DownloadInfo> downloadInfos;


    public List<DownloadInfo> getDownloadInfos() {
        return downloadInfos;
    }

    public void setDownloadInfos(List<DownloadInfo> downloadInfos) {
        this.downloadInfos = downloadInfos;
    }

    public int getHas_next() {
        return has_next;
    }

    public void setHas_next(int has_next) {
        this.has_next = has_next;
    }

    @Override
    public String toString() {
        return "DownloadItem{" +
                "has_next=" + has_next +
                ", downloadInfos=" + downloadInfos +
                '}';
    }
}
