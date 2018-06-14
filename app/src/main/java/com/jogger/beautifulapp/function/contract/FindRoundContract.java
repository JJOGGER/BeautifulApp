package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppMediaArticleData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public interface FindRoundContract {
    interface Model extends BaseModel {
        void getFindRoundTopDatas(OnHttpRequestListener<AppMediaArticleData> listener);

        void getFindRoundDatas(int page, int page_size, OnHttpRequestListener<AppMediaArticleData>
                listener);
    }

    interface View extends BaseView {
        void loadTopDatas(AppMediaArticleData appMediaArticleData);
        void loadDatas(AppMediaArticleData appMediaArticleData);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getFindRoundTopDatas();

        void getFindRoundDatas(int page, int page_size);
    }
}
