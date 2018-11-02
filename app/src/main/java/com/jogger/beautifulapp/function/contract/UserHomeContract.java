package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.UserHomeInfo;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public interface UserHomeContract {
    interface Model extends BaseModel {

        void getUserHomeInfo(int id, OnHttpRequestListener<UserHomeInfo> listener);
    }

    interface View extends BaseView {

        void getUserHomeInfoSuccess(UserHomeInfo userHomeInfo);
    }

    interface Presenter extends IPresenter<View, Model> {

        void getUserHomeInfo(int id);
    }
}
