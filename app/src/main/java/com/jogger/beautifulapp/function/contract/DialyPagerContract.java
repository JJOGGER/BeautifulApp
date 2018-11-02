package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public interface DialyPagerContract {
    interface Model extends BaseModel {
        void getChoiceDescData(int id, OnHttpRequestListener<AppInfo> listener);
    }

    interface View extends BaseView {
        void getChoiceDescDataSuccess(AppInfo appInfo);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getChoiceDescData(int id);
    }
}
