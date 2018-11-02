package com.jogger.beautifulapp.entity;

import com.jogger.beautifulapp.http.download.DownloadState;
import com.jogger.beautifulapp.http.download.HttpDownService;
import com.jogger.beautifulapp.http.download.HttpDownloadOnNextListener;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by jogger on 2018/10/10.
 */
@Entity
public class DownloadInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private long id;
    /*存储位置*/
    private String savePath;
    /*文件总长度*/
    private long countLength;
    /*下载长度*/
    private long readLength;
    /*下载唯一的HttpService*/
    @Transient
    private HttpDownService service;
    /*回调监听*/
    @Transient
    private HttpDownloadOnNextListener listener;
    /*超时设置*/
    private int connectonTime = 6;
    /*state状态数据库保存*/
    private int stateInte;
    private long downloadDate;
    private String title;
    /*url*/
    private String url;
    /*是否需要实时更新下载进度,避免线程的多次切换*/
    private boolean updateProgress;
    private boolean selected;

    public DownloadInfo(String url, HttpDownloadOnNextListener listener) {
        setUrl(url);
        setListener(listener);
    }

    public DownloadInfo(String url) {
        setUrl(url);
    }


    public DownloadInfo() {
    }

    @Generated(hash = 998594527)
    public DownloadInfo(long id, String savePath, long countLength, long readLength,
            int connectonTime, int stateInte, long downloadDate, String title,
            String url, boolean updateProgress, boolean selected) {
        this.id = id;
        this.savePath = savePath;
        this.countLength = countLength;
        this.readLength = readLength;
        this.connectonTime = connectonTime;
        this.stateInte = stateInte;
        this.downloadDate = downloadDate;
        this.title = title;
        this.url = url;
        this.updateProgress = updateProgress;
        this.selected = selected;
    }

   


    public DownloadState getState() {
        switch (getStateInte()) {
            case 0:
                return DownloadState.START;
            case 1:
                return DownloadState.DOWNLOADING;
            case 2:
                return DownloadState.PAUSE;
            case 3:
                return DownloadState.STOP;
            case 4:
                return DownloadState.ERROR;
            case 5:
            default:
                return DownloadState.FINISH;
        }
    }

    public boolean isUpdateProgress() {
        return updateProgress;
    }

    public void setUpdateProgress(boolean updateProgress) {
        this.updateProgress = updateProgress;
    }

    public void setState(DownloadState state) {
        setStateInte(state.getState());
    }


    public int getStateInte() {
        return stateInte;
    }

    public void setStateInte(int stateInte) {
        this.stateInte = stateInte;
    }

    public HttpDownloadOnNextListener getListener() {
        return listener;
    }

    public void setListener(HttpDownloadOnNextListener listener) {
        this.listener = listener;
    }

    public HttpDownService getService() {
        return service;
    }

    public void setService(HttpDownService service) {
        this.service = service;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }


    public long getCountLength() {
        return countLength;
    }

    public void setCountLength(long countLength) {
        this.countLength = countLength;
    }

    public long getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(long downloadDate) {
        this.downloadDate = downloadDate;
    }

    public long getReadLength() {
        return readLength;
    }

    public void setReadLength(long readLength) {
        this.readLength = readLength;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getConnectonTime() {
        return this.connectonTime;
    }

    public void setConnectonTime(int connectonTime) {
        this.connectonTime = connectonTime;
    }

    public boolean getUpdateProgress() {
        return this.updateProgress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "DownloadInfo{" +
                "id=" + id +
                ", savePath='" + savePath + '\'' +
                ", countLength=" + countLength +
                ", readLength=" + readLength +
                ", service=" + service +
                ", listener=" + listener +
                ", connectonTime=" + connectonTime +
                ", stateInte=" + stateInte +
                ", downloadDate=" + downloadDate +
                ", title='" + title + '\'' +
                ", selected='" + selected + '\'' +
                ", url='" + url + '\'' +
                ", updateProgress=" + updateProgress +
                '}';
    }

    public boolean getSelected() {
        return this.selected;
    }
}
