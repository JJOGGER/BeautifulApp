package com.jogger.beautifulapp.function.presenter;

import android.text.TextUtils;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.DownloadInfo;
import com.jogger.beautifulapp.entity.DownloadItem;
import com.jogger.beautifulapp.function.contract.DownloadManageContract;
import com.jogger.beautifulapp.function.model.DownloadManageModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

import java.io.File;

/**
 * Created by jogger on 2018/10/19.
 */
public class DownloadManagePresenter extends BasePresenter<DownloadManageContract.View, DownloadManageContract.Model> implements DownloadManageContract.Presenter {
    private int mPage = 0;
    private int mPageSize = 20;
    private boolean mHasNext;

    public boolean isHasNext() {
        return mHasNext;
    }

    @Override
    public void getDownloadDatas() {
        getModel().getDownloadDatas(mPage, mPageSize, new OnHttpRequestListener<DownloadItem>() {
            @Override
            public void onFailure(int errorCode) {

            }

            @Override
            public void onSuccess(DownloadItem downloadItem) {
                if (unViewAttached() || downloadItem == null || downloadItem.getDownloadInfos() == null)
                    return;
                mHasNext = downloadItem.getHas_next() == 1;
                getView().getDownloadDatasSuccess(downloadItem.getDownloadInfos());
            }
        });
    }

    /**
     * 开始下载
     */
    @Override
    public void startDownload() {
        DownloadInfo downloadInfo = getView().getDownloadInfo();
        if (downloadInfo == null) return;
        getModel().startDownload(downloadInfo);
    }

    /**
     * 重新下载
     */
    @SuppressWarnings("all")
    @Override
    public void reDownload() {
        DownloadInfo downloadInfo = getView().getDownloadInfo();
        if (downloadInfo == null) return;
        File file = new File(getView().getDownloadInfo().getSavePath());
        if (file.exists())
            file.delete();
        if (!TextUtils.isEmpty(downloadInfo.getUrl())) {
            downloadInfo.setReadLength(0);
            downloadInfo.setDownloadDate(System.currentTimeMillis());
            getModel().startDownload(downloadInfo);
        }
    }

    @Override
    public void getMoreDatas() {
        mPage++;
        getModel().getDownloadDatas(mPage, mPageSize, new OnHttpRequestListener<DownloadItem>() {
            @Override
            public void onFailure(int errorCode) {
            }

            @Override
            public void onSuccess(DownloadItem downloadItem) {
                if (unViewAttached() || downloadItem == null || downloadItem
                        .getDownloadInfos() == null)
                    return;
                mHasNext = downloadItem.getHas_next() == 1;
                getView().getMoreDatasSuccess(downloadItem.getDownloadInfos());
            }
        });
    }

    @Override
    public DownloadManageContract.Model attachModel() {
        return new DownloadManageModel();
    }

    @Override
    public void pauseDownload() {
        DownloadInfo downloadInfo = getView().getDownloadInfo();
        if (downloadInfo == null) return;
        downloadInfo.getListener().onPause();
        getModel().pauseDownload(downloadInfo);
    }
}
