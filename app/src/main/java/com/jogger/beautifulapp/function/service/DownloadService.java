package com.jogger.beautifulapp.function.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.DownloadInfo;
import com.jogger.beautifulapp.http.download.DownloadState;
import com.jogger.beautifulapp.http.download.HttpDownloadManager;
import com.jogger.beautifulapp.http.download.HttpDownloadOnNextListener;
import com.jogger.beautifulapp.util.L;

/**
 * Created by jogger on 2018/10/25.
 */
public class DownloadService extends Service {
    private MyBinder mBinder;
    private OnHttpDownloadListener mListener;

    public interface OnHttpDownloadListener {
        void onCompleted(DownloadInfo downloadInfo);

        void onStart(DownloadInfo downloadInfo);

        void updateProgress(DownloadInfo downloadInfo, long readLength, long countLength);

        void onStop(DownloadInfo downloadInfo);

        void onPause(DownloadInfo downloadInfo);

        void onError(DownloadInfo downloadInfo);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mBinder = null;
        return super.onUnbind(intent);
    }

    @SuppressWarnings("unchecked")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            final DownloadInfo downloadInfo = (DownloadInfo) intent.getSerializableExtra(Constant.DOWNLOAD_INFO);
            if (downloadInfo == null)
                return super.onStartCommand(intent, flags, startId);
            downloadInfo.setListener(new HttpDownloadOnNextListener<DownloadInfo>() {
                @Override
                public void onNext(DownloadInfo downloadInfo) {
                    downloadInfo.setState(DownloadState.FINISH);
                    if (mListener != null)
                        mListener.onCompleted(downloadInfo);
                }

                @Override
                public void onStart() {
                    downloadInfo.setState(DownloadState.START);
                    if (mListener != null)
                        mListener.onStart(downloadInfo);
                }

                @Override
                public void onError(Throwable e) {
                    downloadInfo.setState(DownloadState.ERROR);
                    if (mListener != null)
                        mListener.onError(downloadInfo);
                }

                @Override
                public void onPause() {
                    downloadInfo.setState(DownloadState.PAUSE);
                    if (mListener != null)
                        mListener.onPause(downloadInfo);
                }

                @Override
                public void onStop() {
                    downloadInfo.setState(DownloadState.STOP);
                    if (mListener != null)
                        mListener.onStop(downloadInfo);
                }

                @Override
                public void onComplete() {
                }

                @Override
                public void updateProgress(long readLength, long countLength) {
                    L.i("---------updateProgress"+mListener);
                    downloadInfo.setState(DownloadState.DOWNLOADING);
                    downloadInfo.setReadLength(readLength);
                    downloadInfo.setCountLength(countLength);
                    if (mListener != null)
                        mListener.updateProgress(downloadInfo, readLength, countLength);
                }
            });
            HttpDownloadManager.getHttpDownloadManager().startDownload(downloadInfo);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public class MyBinder extends Binder {
        public void setOnHttpDownloadListener(OnHttpDownloadListener listener) {
            mListener = listener;
        }
    }
}
