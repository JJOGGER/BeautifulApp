package com.jogger.beautifulapp.function.contract;


import com.jogger.beautifulapp.entity.AppCategoryData;
import com.jogger.beautifulapp.function.model.IDescBaseModel;
import com.jogger.beautifulapp.function.presenter.IDescBasePresenter;
import com.jogger.beautifulapp.function.ui.view.DescBaseView;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public interface CategoryContract {
    interface Model extends IDescBaseModel {
        void getCategoryDatas(OnHttpRequestListener<AppCategoryData> listener);
    }

    interface View extends DescBaseView {
        void getCategoryDatasSuccess(AppCategoryData appCategoryData);
    }

    interface Presenter extends IDescBasePresenter<View, Model> {
        void getCategoryDatas();
    }
}
