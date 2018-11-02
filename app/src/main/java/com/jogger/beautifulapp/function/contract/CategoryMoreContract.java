package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.entity.AppCategoryMoreData;
import com.jogger.beautifulapp.entity.TopApp;
import com.jogger.beautifulapp.function.model.IDescBaseModel;
import com.jogger.beautifulapp.function.presenter.IDescBasePresenter;
import com.jogger.beautifulapp.function.ui.view.DescBaseView;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

import java.util.List;

/**
 * Created by jogger on 2018/11/1.
 */
public interface CategoryMoreContract {
    interface Model extends IDescBaseModel {
        void getCategoryDatas(int id, int page, int pageSize, OnHttpRequestListener<AppCategoryMoreData> listener);
    }

    interface View extends DescBaseView {
        void getCategoryDatasSuccess(List<TopApp> topApps);

        void getMoreDatasSuccess(List<TopApp> topApps);

        void getMoreDatasFail();
    }

    interface Presenter extends IDescBasePresenter<View, Model> {
        void getCategoryDatas();

        void getMoreDatas();
    }
}
