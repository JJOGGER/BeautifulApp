package com.jogger.beautifulapp.function.contract;


import com.jogger.beautifulapp.entity.AppCollectData;
import com.jogger.beautifulapp.function.model.IDescBaseModel;
import com.jogger.beautifulapp.function.presenter.IDescBasePresenter;
import com.jogger.beautifulapp.function.ui.view.DescBaseView;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public interface UserHomeCollectContract {
    interface Model extends IDescBaseModel {
        void setUserId(int userId);

        void getUserCollectDatas(int page, int page_size,
                                 OnHttpRequestListener<AppCollectData> listener);
    }

    interface View extends DescBaseView {
        void getUserCollectDatasSuccess(AppCollectData appCollectData);
    }

    interface Presenter extends IDescBasePresenter<View, Model> {
        void getUserCollectDatas(int page, int page_size);
    }
}
