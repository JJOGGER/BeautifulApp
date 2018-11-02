package com.jogger.beautifulapp.control;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import com.jogger.beautifulapp.BuildConfig;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.db.DBManager;
import com.jogger.beautifulapp.entity.DownloadInfo;
import com.jogger.beautifulapp.entity.DownloadUrl;
import com.jogger.beautifulapp.function.service.DownloadService;
import com.jogger.beautifulapp.http.download.HttpDownloadOnNextListener;
import com.jogger.beautifulapp.util.T;
import com.jogger.beautifulapp.util.Util;

import java.io.File;
import java.util.List;

/**
 * Created by jogger on 2018/11/1.
 */
public class DownloadControl {
    public static void startDownload(DownloadUrl downloadUrl) {
        startDownload(downloadUrl, null);
    }


    private static void directDownload(DownloadUrl downloadUrl, HttpDownloadOnNextListener listener) {
        if (downloadUrl.getUrl().endsWith(".html") || !downloadUrl.getUrl().contains(".apk")) {
            //这是一个跳转下载连接，不能直接下载
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(downloadUrl.getUrl()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Util.getApp().startActivity(intent);
            return;
        }
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + downloadUrl + ".apk");
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(Util.getApp(), BuildConfig.APPLICATION_ID + ".fileProvider", file);
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            Util.getApp().startActivity(intent);
            return;
        }
        DBManager dbManager = DBManager.getInstance();
        List<DownloadInfo> downloadInfos = dbManager.queryDownloadAll();
        DownloadInfo downloadInfo = null;
        if (downloadInfos.isEmpty()) {
            downloadInfo = createDownloadInfo(downloadUrl);
        } else {
            for (int i = 0; i < downloadInfos.size(); i++) {
                //本地存在该任务
                if (downloadInfos.get(i).getUrl().equals(downloadUrl.getUrl())) {
                    downloadInfo = downloadInfos.get(i);
                    break;
                }
            }
        }
        //本地不存在此任务
        if (downloadInfo == null) {
            downloadInfo = createDownloadInfo(downloadUrl);
        }
        downloadInfo.setListener(listener);
        T.show("开始下载...");
        Intent intent = new Intent(Util.getApp(), DownloadService.class);
        intent.putExtra(Constant.DOWNLOAD_INFO, downloadInfo);
        Util.getApp().startService(intent);
    }

    public static void startDownload(DownloadUrl downloadUrl, HttpDownloadOnNextListener listener) {
        if (downloadUrl == null) return;
        switch (downloadUrl.getChannel()) {
            case Constant.CHANNEL_DIRECT:
                directDownload(downloadUrl, listener);
                break;
            case Constant.CHANNEL_GOOGLE:
                break;
            case Constant.CHANNEL_WANDOUJIA:
                break;
        }
    }

    private static DownloadInfo createDownloadInfo(DownloadUrl downloadUrl) {
        DownloadInfo downloadInfo;
        downloadInfo = new DownloadInfo(downloadUrl.getUrl());
        downloadInfo.setId(downloadUrl.getId());
        downloadInfo.setTitle(downloadUrl.getName());
        downloadInfo.setConnectonTime(60);
        downloadInfo.setUpdateProgress(true);
        downloadInfo.setSavePath(
                Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                        + File.separator + downloadUrl.getName() + ".apk");
        downloadInfo.setDownloadDate(System.currentTimeMillis());
        DBManager.getInstance().saveDownload(downloadInfo);
        return downloadInfo;
    }
}
