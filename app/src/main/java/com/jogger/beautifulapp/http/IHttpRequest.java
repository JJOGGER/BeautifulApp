package com.jogger.beautifulapp.http;


import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by jogger on 2018/4/14.请求接口
 */

interface IHttpRequest<T> {
    void test(String account, final OnHttpRequestListener<T>
            listener);

    void getDialyDatas(int page, int page_size, int platform, OnHttpRequestListener<T> listener);

    void getFindRecentDatas(int page, int page_size, int platform, OnHttpRequestListener<T>
            listener);

    void getFindChoiceDatas(String startDate,int platform, OnHttpRequestListener<T> listener);

    void getFindRoundTopDatas(int platform, OnHttpRequestListener<T> listener);

    void getFindRoundDatas(int page, int page_size, int platform, OnHttpRequestListener<T>
            listener);
}
