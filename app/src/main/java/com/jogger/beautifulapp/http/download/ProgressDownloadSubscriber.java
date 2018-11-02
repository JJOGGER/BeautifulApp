package com.jogger.beautifulapp.http.download;

import android.os.Handler;

import com.jogger.beautifulapp.db.DBManager;
import com.jogger.beautifulapp.entity.DownloadInfo;
import com.jogger.beautifulapp.http.listener.DownloadProgressListener;
import com.jogger.beautifulapp.util.L;

import java.lang.ref.SoftReference;

import io.reactivex.observers.DisposableObserver;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * Created by WZG on 2016/7/16.
 */
public class ProgressDownloadSubscriber<T> extends DisposableObserver<T> implements DownloadProgressListener {
    //弱引用结果回调
    private SoftReference<HttpDownloadOnNextListener> mSubscriberOnNextListener;
    /*下载数据*/
    private DownloadInfo mDownloadInfo;
    private Handler mHandler;


    public ProgressDownloadSubscriber(DownloadInfo mDownloadInfo, Handler handler) {
        this.mSubscriberOnNextListener = new SoftReference<>(mDownloadInfo.getListener());
        this.mDownloadInfo = mDownloadInfo;
        this.mHandler = handler;
    }


    public void setDownloadInfo(DownloadInfo downInfo) {
        this.mSubscriberOnNextListener = new SoftReference<>(downInfo.getListener());
        this.mDownloadInfo = downInfo;
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onStart();
        }
        L.i("------------onStart");
        mDownloadInfo.setState(DownloadState.START);
    }


    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        L.e("------onError:" + e.getMessage());
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onError(e);
        }
        HttpDownloadManager.getHttpDownloadManager().remove(mDownloadInfo);
        mDownloadInfo.setState(DownloadState.ERROR);
        DBManager.getDaoSession().getDownloadInfoDao().update(mDownloadInfo);
    }

    /**
     * 完成，隐藏ProgressDialog
     */

    @Override
    public void onComplete() {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onComplete();
        }
        L.i("------------onComplete");
        HttpDownloadManager.getHttpDownloadManager().remove(mDownloadInfo);
        mDownloadInfo.setState(DownloadState.FINISH);
        mDownloadInfo.setReadLength(0);
        L.e("----------downinfo:" + mDownloadInfo.hashCode() + ":" + mDownloadInfo.getTitle());
        DBManager.getDaoSession().getDownloadInfoDao().update(mDownloadInfo);
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onNext(t);
        }
        L.i("------------onNext");
    }

    @Override
    public void update(long read, long count, boolean done) {
        if (mDownloadInfo.getCountLength() > count) {
            read = mDownloadInfo.getCountLength() - count + read;
        } else {
            mDownloadInfo.setCountLength(count);
        }
        mDownloadInfo.setReadLength(read);
        if (mSubscriberOnNextListener.get() == null || !mDownloadInfo.isUpdateProgress()) return;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                /*如果暂停或者停止状态延迟，不需要继续发送回调，影响显示*/
                if (mDownloadInfo.getState() == DownloadState.PAUSE || mDownloadInfo.getState() == DownloadState.STOP)
                    return;
                if (mDownloadInfo.getReadLength() == mDownloadInfo.getCountLength()) {
                    mDownloadInfo.setState(DownloadState.FINISH);
                    mSubscriberOnNextListener.get().onComplete();
                } else {
                    mDownloadInfo.setState(DownloadState.DOWNLOADING);
                    mSubscriberOnNextListener.get().updateProgress(mDownloadInfo.getReadLength(), mDownloadInfo.getCountLength());
                }
            }
        });
    }

}
