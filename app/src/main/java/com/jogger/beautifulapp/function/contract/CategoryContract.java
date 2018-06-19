package com.jogger.beautifulapp.function.contract;


import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppCategoryData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public interface CategoryContract {
    interface Model extends BaseModel {
        void getCategoryDatas(OnHttpRequestListener<AppCategoryData> listener);
    }

    interface View extends BaseView {
        void getCategoryDatasSuccess(AppCategoryData appCategoryData);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getCategoryDatas();
    }
}
