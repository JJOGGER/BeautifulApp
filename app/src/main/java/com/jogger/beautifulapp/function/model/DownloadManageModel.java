package com.jogger.beautifulapp.function.model;

import android.os.AsyncTask;

import com.jogger.beautifulapp.db.DBManager;
import com.jogger.beautifulapp.entity.DownloadInfo;
import com.jogger.beautifulapp.entity.DownloadInfoDao;
import com.jogger.beautifulapp.entity.DownloadItem;
import com.jogger.beautifulapp.function.contract.DownloadManageContract;
import com.jogger.beautifulapp.http.download.HttpDownloadManager;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by jogger on 2018/10/19.
 */
public class DownloadManageModel implements DownloadManageContract.Model {
    @Override
    public void getDownloadDatas(int page, int pageSize, OnHttpRequestListener<DownloadItem> listener) {
        new DatasAsyncTask(page, pageSize, listener).execute();
    }

    @Override
    public void startDownload(DownloadInfo downloadInfo) {
        HttpDownloadManager.getHttpDownloadManager().startDownload(downloadInfo);
    }

    @Override
    public void pauseDownload(DownloadInfo downloadInfo) {
        HttpDownloadManager.getHttpDownloadManager().pause(downloadInfo);
    }

    private static class DatasAsyncTask extends AsyncTask<Void, Void, DownloadItem> {

        private OnHttpRequestListener<DownloadItem> mListener;
        private int mPage;
        private int mPageSize;

        private DatasAsyncTask(int page, int pageSize, OnHttpRequestListener<DownloadItem> listener) {
            mPage = page;
            mPageSize = pageSize;
            mListener = listener;
        }

        @Override
        protected DownloadItem doInBackground(Void... voids) {
            QueryBuilder<DownloadInfo> queryBuilder = DBManager.getDaoSession().getDownloadInfoDao().queryBuilder();
            List<DownloadInfo> downloadInfos = queryBuilder
                    .offset(mPage * mPageSize)
                    .limit(mPageSize)
                    .orderDesc(DownloadInfoDao.Properties.DownloadDate)
                    .list();
            DownloadItem downloadItem = new DownloadItem();
            long count = queryBuilder.count();
            downloadItem.setHas_next(count > mPage * mPageSize + mPageSize ? 1 : 0);
            downloadItem.setDownloadInfos(downloadInfos);
            return downloadItem;
        }

        @Override
        protected void onPostExecute(DownloadItem downloadItem) {
            super.onPostExecute(downloadItem);
            if (mListener != null)
                mListener.onSuccess(downloadItem);
        }
    }
}
