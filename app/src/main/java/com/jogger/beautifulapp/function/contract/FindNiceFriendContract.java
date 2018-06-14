package com.jogger.beautifulapp.function.contract;


import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppNiceFriendData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public interface FindNiceFriendContract {
    interface Model extends BaseModel {
        void getFindNiceFriendDatas(OnHttpRequestListener<AppNiceFriendData> listener);
    }

    interface View extends BaseView {
        void loadDatas(AppNiceFriendData appData);
    }

    interface Presenter extends IPresenter<View, Model> {
        void getFindNickFriendDatas();
    }
}
