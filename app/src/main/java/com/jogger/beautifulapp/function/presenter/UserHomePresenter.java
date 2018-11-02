package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.UserHomeInfo;
import com.jogger.beautifulapp.function.contract.UserHomeContract;
import com.jogger.beautifulapp.function.model.UserHomeModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class UserHomePresenter extends BasePresenter<UserHomeContract.View, UserHomeContract
        .Model> implements UserHomeContract.Presenter {
    @Override
    public UserHomeContract.Model attachModel() {
        return new UserHomeModel();
    }

    @Override
    public void getUserHomeInfo(int id) {
        getView().showLoadingWindow();
        getModel().getUserHomeInfo(id, new OnHttpRequestListener<UserHomeInfo>() {
            @Override
            public void onFailure(int errorCode) {
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
            }

            @Override
            public void onSuccess(UserHomeInfo userHomeInfo) {
                if (unViewAttached() || userHomeInfo == null) return;
                getView().dismissLoadingWindow();
                getView().getUserHomeInfoSuccess(userHomeInfo);
            }
        });
    }
}
