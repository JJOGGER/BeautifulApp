package com.jogger.beautifulapp.function.contract;


import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppCollectData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public interface UserHomeCollectContract {
    interface Model extends BaseModel {
        void setUserId(int userId);

        void getUserCollectDatas(int page, int page_size,
                                 OnHttpRequestListener<AppCollectData> listener);
    }

    interface View extends BaseView {
        void getUserCollectDatasSuccess(AppCollectData appCollectData);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getUserCollectDatas(int page, int page_size);
    }
}
