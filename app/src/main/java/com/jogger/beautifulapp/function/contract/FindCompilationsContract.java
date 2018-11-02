package com.jogger.beautifulapp.function.contract;


import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.Album;
import com.jogger.beautifulapp.entity.AppCompilationDescData;
import com.jogger.beautifulapp.entity.AppCompilationsData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

import java.util.List;

public interface FindCompilationsContract {
    interface Model extends BaseModel {
        void getFindCompilationsDatas(int page, int page_size,
                                      OnHttpRequestListener<AppCompilationsData> listener);

        void getCompilationDescDatas(int id, OnHttpRequestListener<AppCompilationDescData> listener);
    }

    interface View extends BaseView {
        void getFindCompilationsDatasSuccess(List<Album> albums);

        void getMoreDatasSuccess(List<Album> albums);

        void getMoreDatasFail();

        void getCompilationDescDatasSuccess(AppCompilationDescData appCompilationDescData);

        void getCompilationDescDatasFail();
    }

    interface Presenter extends IPresenter<View, Model> {
        void getFindCompilationsDatas();

        void getMoreDatas();

        void getCompilationDescDatas(int id);
    }
}
