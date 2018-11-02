package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.DownloadInfo;
import com.jogger.beautifulapp.entity.DownloadItem;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

import java.util.List;

/**
 * Created by jogger on 2018/10/19.
 */
public interface DownloadManageContract {
    interface Model extends BaseModel {
        void getDownloadDatas(int page, int pageSize, OnHttpRequestListener<DownloadItem> listener);

        void startDownload(DownloadInfo downloadInfo);

        void pauseDownload(DownloadInfo downloadInfo);
    }

    interface View extends BaseView {
        DownloadInfo getDownloadInfo();

        void getDownloadDatasSuccess(List<DownloadInfo> downloadInfos);

        void getMoreDatasSuccess(List<DownloadInfo> downloadInfos);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getDownloadDatas();

        void startDownload();

        void reDownload();

        void getMoreDatas();

        void pauseDownload();
    }
}
