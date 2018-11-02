package com.jogger.beautifulapp.base;


public interface BaseView {
    int getLayoutId();

    void showLoadingWindow();

    void dismissLoadingWindow();
}
