package com.jogger.beautifulapp.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jogger.beautifulapp.util.Util;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jogger on 2018/5/4.
 * 网络请求基类
 */

abstract class BaseHttpAction {
    IHttpRequest mHttpRequest;


    BaseHttpAction() {
        initRetrofit();
    }

    private void initRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(new Cache(Util.getApp().getCacheDir(), 10 * 1024 * 1024)).connectTimeout
                (10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);
        builder.interceptors().add(new ReceivedCookiesInterceptor());
        builder.interceptors().add(new AddCookiesInterceptor());
        builder.interceptors().add(new CacheInterceptor());
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RequestService requestService = retrofit.create(RequestService.class);
        mHttpRequest = getHttpRequest(requestService);
    }

    abstract IHttpRequest getHttpRequest(RequestService requestService);

    abstract String getBaseUrl();
}
