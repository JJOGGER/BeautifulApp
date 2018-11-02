package com.jogger.beautifulapp.http.download;

import android.os.Handler;
import android.os.Looper;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jogger.beautifulapp.db.DBManager;
import com.jogger.beautifulapp.entity.DownloadInfo;
import com.jogger.beautifulapp.http.download.exception.HttpTimeException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by jogger on 2018/10/10.
 */
public class HttpDownloadManager {
    /*记录下载数据*/
    private Set<DownloadInfo> mDownloadInfos;
    /*回调sub队列*/
    private HashMap<String, ProgressDownloadSubscriber> mSubscriberHashMap;
    private volatile static HttpDownloadManager INSTANCE;
    /*数据库类*/
    private DBManager mDBManager;
    /*下载进度回掉主线程*/
    private Handler mHandler;

    private HttpDownloadManager() {
        mDownloadInfos = new HashSet<>();
        mSubscriberHashMap = new HashMap<>();
        mDBManager = DBManager.getInstance();
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 获取单例
     */
    public static HttpDownloadManager getHttpDownloadManager() {
        if (INSTANCE == null) {
            synchronized (HttpDownloadManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDownloadManager();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 开始下载
     */
    @SuppressWarnings("unchecked")
    public void startDownload(final DownloadInfo info) {
        /*正在下载不处理*/
        if (info == null || mSubscriberHashMap.get(info.getUrl()) != null) {
            if (info == null) return;
            mSubscriberHashMap.get(info.getUrl()).setDownloadInfo(info);
            return;
        }
        /*添加回调处理类*/
        ProgressDownloadSubscriber subscriber = new ProgressDownloadSubscriber(info, mHandler);
        /*记录回调sub*/
        mSubscriberHashMap.put(info.getUrl(), subscriber);
        /*获取service，多次请求公用一个sercie*/
        HttpDownService httpService;
        if (mDownloadInfos.contains(info)) {
            httpService = info.getService();
        } else {
            DownloadInterceptor interceptor = new DownloadInterceptor(subscriber);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //手动创建一个OkHttpClient并设置超时时间
            builder.connectTimeout(info.getConnectonTime(), TimeUnit.SECONDS);
            builder.addInterceptor(interceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(getBasUrl(info.getUrl()))
//                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            httpService = retrofit.create(HttpDownService.class);
            info.setService(httpService);
            mDownloadInfos.add(info);
        }
        if (info.getReadLength() == info.getCountLength() && info.getReadLength() != 0) {
            subscriber.onComplete();
            return;
        }
        httpService.download("bytes=" + info.getReadLength() + "-", info.getUrl())
                /*指定线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*失败后的retry配置*/
//                .retryWhen(new RetryWhenNetworkException())
                /*读取下载写入文件*/
                .map(new Function<ResponseBody, Object>() {
                    @Override
                    public Object apply(ResponseBody responseBody) throws Exception {
                        writeCaches(responseBody, new File(info.getSavePath()), info);
                        return info;
                    }
                })
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    private String getBasUrl(String url) {
        String head = "";
        int index = url.indexOf("://");
        if (index != -1) {
            head = url.substring(0, index + 3);
            url = url.substring(index + 3);
        }
        index = url.indexOf("/");
        if (index != -1) {
            url = url.substring(0, index + 1);
        }
        return head + url;
    }

    /**
     * 停止下载
     */
    public void stopDownload(DownloadInfo info) {
        if (info == null) return;
        info.setState(DownloadState.STOP);
        info.getListener().onStop();
        if (mSubscriberHashMap.containsKey(info.getUrl())) {
            ProgressDownloadSubscriber subscriber = mSubscriberHashMap.get(info.getUrl());
            subscriber.dispose();
            mSubscriberHashMap.remove(info.getUrl());
        }
        /*保存数据库信息和本地文件*/
        mDBManager.saveDownload(info);
    }


    /**
     * 暂停下载
     *
     * @param info
     */
    public void pause(DownloadInfo info) {
        if (info == null) return;
        info.setState(DownloadState.PAUSE);
        info.getListener().onPause();
        if (mSubscriberHashMap.containsKey(info.getUrl())) {
            ProgressDownloadSubscriber subscriber = mSubscriberHashMap.get(info.getUrl());
            subscriber.dispose();
            mSubscriberHashMap.remove(info.getUrl());
        }
        /*这里需要讲info信息写入到数据中，可自由扩展，用自己项目的数据库*/
        mDBManager.updateDownload(info);
    }

    /**
     * 停止全部下载
     */
    public void stopAllDown() {
        for (DownloadInfo DownloadInfo : mDownloadInfos) {
            stopDownload(DownloadInfo);
        }
        mSubscriberHashMap.clear();
        mDownloadInfos.clear();
    }

    /**
     * 暂停全部下载
     */
    public void pauseAll() {
        for (DownloadInfo DownloadInfo : mDownloadInfos) {
            pause(DownloadInfo);
        }
        mSubscriberHashMap.clear();
        mDownloadInfos.clear();
    }


    /**
     * 返回全部正在下载的数据
     *
     * @return
     */
    public Set<DownloadInfo> getDownloadInfos() {
        return mDownloadInfos;
    }

    /**
     * 移除下载数据
     *
     * @param info
     */
    public void remove(DownloadInfo info) {
        mSubscriberHashMap.remove(info.getUrl());
        mDownloadInfos.remove(info);
    }


    /**
     * 写入文件
     *
     * @param file
     * @param info
     * @throws IOException
     */
    public void writeCaches(ResponseBody responseBody, File file, DownloadInfo info) {
        try {
            RandomAccessFile randomAccessFile = null;
            FileChannel channelOut = null;
            InputStream inputStream = null;
            try {
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                long contentLength = responseBody.contentLength();
                long allLength = 0 == info.getCountLength() ? contentLength : info.getReadLength() + contentLength;

                inputStream = responseBody.byteStream();
                randomAccessFile = new RandomAccessFile(file, "rwd");
                channelOut = randomAccessFile.getChannel();
                MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                        info.getReadLength(), allLength - info.getReadLength());
                byte[] buffer = new byte[1024 * 4];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    mappedBuffer.put(buffer, 0, len);
                }
            } catch (IOException e) {
                throw new HttpTimeException(e.getMessage());
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (channelOut != null) {
                    channelOut.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            }
        } catch (IOException e) {
            throw new HttpTimeException(e.getMessage());
        }
    }
}
