package com.jogger.beautifulapp.http.download;

/**
 * Created by jogger on 2018/10/10.
 */
public enum DownloadState {
    START(0),
    DOWNLOADING(1),
    PAUSE(2),
    STOP(3),
    ERROR(4),
    FINISH(5);
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    DownloadState(int state) {
        this.state = state;
    }
}
