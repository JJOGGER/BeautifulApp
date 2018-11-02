package com.jogger.beautifulapp.http.download;

import com.jogger.beautifulapp.http.listener.DownloadProgressListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by jogger on 2018/10/10.
 */
public class DownloadInterceptor implements Interceptor {
    private DownloadProgressListener mListener;

    public DownloadInterceptor(DownloadProgressListener listener) {
        mListener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response=chain.proceed(chain.request());
        return response.newBuilder()
                .body(new DownloadResponseBody(response.body(),mListener))
                .build();
    }
}
