package com.jogger.beautifulapp.base;


public interface IPresenter<V extends BaseView> {
    void attachView(V view);

    void detachView();
}
