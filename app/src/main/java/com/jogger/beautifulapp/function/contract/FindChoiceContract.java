package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.FindChoiceData;
import com.jogger.beautifulapp.entity.MediaArticle;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

import java.util.List;

/**
 * Created by Jogger on 2018/6/12.
 */

public interface FindChoiceContract {
    interface Model extends BaseModel {
        void getFindChoiceDatas(String startDate, OnHttpRequestListener<FindChoiceData> listener);

        void getChoiceDescData(int id, OnHttpRequestListener<AppInfo> listener);
    }

    interface View extends BaseView {
        void getFindChoiceDatasSuccess(List<MediaArticle> appData);

        void getMoreDatasSuccess(List<MediaArticle> appData);

        void getMoreDatasFail();

        void getChoiceDescDataSuccess(AppInfo appInfo);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getFindChoiceDatas();

        void getMoreDatas();

        void getChoiceDescData(int id);
    }
}
