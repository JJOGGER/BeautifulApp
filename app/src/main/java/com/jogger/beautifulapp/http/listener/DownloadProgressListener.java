package com.jogger.beautifulapp.http.listener;

/**
 * Created by jogger on 2018/10/10.
 */
public interface DownloadProgressListener {
    /**
     * 下载进度
     *
     * @param read
     * @param count
     * @param done
     */
    void update(long read, long count, boolean done);
}
