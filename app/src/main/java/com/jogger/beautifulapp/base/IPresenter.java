package com.jogger.beautifulapp.base;


public interface IPresenter<V extends BaseView, M extends BaseModel> {
    void attachView(V view);

    void detachView();

    M attachModel();

}
