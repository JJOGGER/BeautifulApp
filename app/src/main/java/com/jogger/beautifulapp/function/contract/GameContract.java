package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppInfoData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public interface GameContract {
    interface Model extends BaseModel {
        void getGameDatas(int page, int pageSize, OnHttpRequestListener<AppInfoData> listener);
    }

    interface View extends BaseView {
        void getGameDatasSuccess(AppInfoData appData);

        void updateDate(int weekResId, String month, int day);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getGameDatas(int page, int pageSize);

        void updateDate(String date);
    }
}
