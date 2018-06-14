package com.jogger.beautifulapp.function.contract;


import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppCompilationsData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public interface FindCompilationsContract {
    interface Model extends BaseModel {
        void getFindCompilationsDatas(int page, int page_size,
                                      OnHttpRequestListener<AppCompilationsData> listener);
    }

    interface View extends BaseView {
        void loadDatas(AppCompilationsData appData);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getFindCompilationsDatas(int page, int page_size);
    }
}
