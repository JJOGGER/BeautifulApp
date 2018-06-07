package com.jogger.beautifulapp.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by jogger on 2018/3/6.
 */

public class ReceivedCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        //这里获取请求返回的cookie
//        List<String> headers = originalResponse.headers("Set-Cookie");

        return chain.proceed(chain.request());
    }
}
