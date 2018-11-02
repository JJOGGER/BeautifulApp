package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by jogger on 2018/10/29.
 */
public interface FindRoundHeaderContract {
    interface Model extends BaseModel {
        void getHeaderDescDatas(int id, OnHttpRequestListener<AppInfo> listener);
    }

    interface View extends BaseView {
        void getHeaderDescDatasSuccess(AppInfo appInfo);
        void getHeaderDescDatasFail();
    }

    interface Presenter extends IPresenter<View, Model> {
        void getHeaderDescDatas(int id);
    }
}
