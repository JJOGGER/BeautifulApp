package com.jogger.beautifulapp.function.contract;


import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public interface UserHomeRecomendContract {
    interface Model extends BaseModel {
        void setUserId(int userId);

        void getUserRecommendDatas(int page, int page_size,
                                   OnHttpRequestListener<AppRecentData> listener);
    }

    interface View extends BaseView {
        void getUserRecommendDatasSuccess(AppRecentData appRecentData);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getUserRecommendDatas(int page, int page_size);
    }
}
