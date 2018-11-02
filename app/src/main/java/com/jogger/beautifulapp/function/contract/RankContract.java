package com.jogger.beautifulapp.function.contract;


import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppSocialArticleData;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public interface RankContract {
    interface Model extends BaseModel {
        void getRankDatas(int page, int page_size, OnHttpRequestListener<AppSocialArticleData>
                listener);

        void getRecentDescData(int id, OnHttpRequestListener<RecentAppData> listener);
    }

    interface View extends BaseView {
        void getRankDatasSuccess(AppSocialArticleData appSocialArticleData);

        void getRecentDescDataSuccess(RecentAppData appRecentData);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getRankDatas(int page, int page_size);

        void getRecentDescData(int id);

    }
}
