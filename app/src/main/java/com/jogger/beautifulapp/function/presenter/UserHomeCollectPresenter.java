package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppCollectData;
import com.jogger.beautifulapp.function.contract.UserHomeCollectContract;
import com.jogger.beautifulapp.function.model.UserHomeCollectModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class UserHomeCollectPresenter extends BasePresenter<UserHomeCollectContract.View,
        UserHomeCollectContract
                .Model> implements UserHomeCollectContract.Presenter {
    public UserHomeCollectPresenter(int userId) {
        mModle.setUserId(userId);
    }

    @Override
    public UserHomeCollectContract.Model attachModel() {
        return new UserHomeCollectModel();
    }

    @Override
    public void getUserCollectDatas(int page, int page_size) {
        mModle.getUserCollectDatas(page, page_size, new OnHttpRequestListener<AppCollectData>() {
            @Override
            public void onFailure(int errorCode) {

            }

            @Override
            public void onSuccess(AppCollectData appCollectData) {
                if (mView == null) return;
                mView.getUserCollectDatasSuccess(appCollectData);
            }
        });
    }
}
