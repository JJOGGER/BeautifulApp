package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.AppMediaArticleData;
import com.jogger.beautifulapp.entity.MediaArticle;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

import java.util.List;


public interface FindRoundContract {
    interface Model extends BaseModel {
        void getFindRoundTopDatas(OnHttpRequestListener<AppMediaArticleData> listener);

        void getFindRoundDatas(int page, int page_size, OnHttpRequestListener<AppMediaArticleData>
                listener);

        void getDescDatas(int id, OnHttpRequestListener<AppInfo> listener);
    }

    interface View extends BaseView {
        void getFindRoundTopDatasSuccess(List<MediaArticle> mediaArticles);

        void getFindRoundDatasSuccess(List<MediaArticle> mediaArticles);

        void getMoreDatasSuccess(List<MediaArticle> mediaArticles);

        void getMoreDatasFail();

        void getDescDatasSuccess(AppInfo info);

        void getDescDatasFail();
    }

    interface Presenter extends IPresenter<View, Model> {
        void getFindRoundTopDatas();

        void getFindRoundDatas();

        void getMoreDatas();

        void getDescDatas(int id);
    }
}
