package com.jogger.beautifulapp.http;


import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by jogger on 2018/4/14.请求接口
 */

interface IHttpRequest<T> {
    void test(String account, final OnHttpRequestListener<T>
            listener);


}
