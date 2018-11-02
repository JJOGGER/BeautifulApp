package com.jogger.beautifulapp.function.contract;


import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

import java.util.List;

public interface TagsMoreContract {
    interface Model extends BaseModel {
        void getTagsMoreData(int id, int page, int pageSize, OnHttpRequestListener<AppRecentData> listener);
    }

    interface View extends BaseView {
        void getTagsDataSuccess(List<RecentAppData> recentAppDatas);

        void getMoreDatasSuccess(List<RecentAppData> recentAppDatas);

        void getMoreDatasFail();

    }

    interface Presenter extends IPresenter<View, Model> {
        void getTagsDatas(boolean isRefresh);

        void getMoreDatas();
    }
}
