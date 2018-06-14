package com.jogger.beautifulapp.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jogger on 2018/3/6.请求拦截
 */

 class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
//        String cookie = UserSharePreUtil.getInstance(context).getString(ConstantUtil.APP_COOKIE, "");
//        添加cookie
        builder.addHeader("Authorization", "zui NjkwYTc1ZGM2MDRjMjIwZmIxZjg2M2E0YzczZTg4YWI6MTUyODkwNTAwMzow");
        builder.addHeader("LockUser-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)");
        return chain.proceed(builder.build());
    }
}
