package com.jogger.beautifulapp.function.contract;


import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public interface FindRecentContract {
    interface Model extends BaseModel {
        void getRecentDatas(int page, int pageSize, OnHttpRequestListener<AppRecentData> listener);
    }

    interface View extends BaseView {
        void loadDatas(AppRecentData appData);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getRecentDatas(int page, int pageSize);
    }
}
