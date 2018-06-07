package com.jogger.beautifulapp.http;

/**
 * Created by Jogger on 2018/6/3.
 */

public interface OnHttpRequestCallback<T> {
    void onFailure(int errorCode);

    void onSuccess(T t);
}
