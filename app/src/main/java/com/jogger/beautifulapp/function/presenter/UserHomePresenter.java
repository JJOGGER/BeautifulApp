package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.function.contract.UserHomeContract;
import com.jogger.beautifulapp.function.model.UserHomeModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class UserHomePresenter extends BasePresenter<UserHomeContract.View, UserHomeContract
        .Model> implements UserHomeContract.Presenter {
    public UserHomePresenter(int userId){

    }
    @Override
    public UserHomeContract.Model attachModel() {
        return new UserHomeModel();
    }

    @Override
    public void getUserRecommendDatas(int page, int page_size) {
        mModle.getUserRecommendDatas(page, page_size, new OnHttpRequestListener<AppRecentData>() {
            @Override
            public void onFailure(int errorCode) {

            }

            @Override
            public void onSuccess(AppRecentData appRecentData) {
                if (mView == null) return;
                mView.getUserRecommendDatasSuccess(appRecentData);
            }
        });
    }
}
