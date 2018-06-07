package com.jogger.beautifulapp.http.listener;

/**
 * Created by jogger on 2018/1/10.
 */

public interface OnHttpRequestListener<T> {
    void onFailure(int errorCode);

    void onSuccess(T t);
}
