package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.FindChoiceData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by Jogger on 2018/6/12.
 */

public interface FindChoiceContract {
    interface Model extends BaseModel {
        void getFindChoiceDatas(String startDate, OnHttpRequestListener<FindChoiceData> listener);
    }

    interface View extends BaseView {
        void loadDatas(FindChoiceData appData);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getFindChoiceDatas(String startDate);
    }
}
