package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by Jogger on 2018/6/24.
 */

public interface RecentDescContract {
    interface Model extends BaseModel {
        void getDescData(int id, OnHttpRequestListener<RecentAppData> listener);
    }

    interface View extends BaseView {
        void getDescDataSuccess(RecentAppData appData);
    }

    interface Presenter extends IPresenter<RecentDescContract.View, RecentDescContract.Model> {
        void getDescData(int id);
    }
}
