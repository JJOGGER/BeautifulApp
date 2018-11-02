package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.function.contract.UserHomeRecomendContract;
import com.jogger.beautifulapp.function.model.UserHomeRecomendModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;


public class UserHomeRecomendPresenter extends BasePresenter<UserHomeRecomendContract.View,
        UserHomeRecomendContract
                .Model> implements UserHomeRecomendContract.Presenter {
    public UserHomeRecomendPresenter(int userId) {
        getModel().setUserId(userId);
    }

    @Override
    public UserHomeRecomendContract.Model attachModel() {
        return new UserHomeRecomendModel();
    }

    @Override
    public void getUserRecommendDatas(int page, int page_size) {
        getModel().getUserRecommendDatas(page, page_size, new OnHttpRequestListener<AppRecentData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("-----errorCode:"+errorCode);
            }

            @Override
            public void onSuccess(AppRecentData appRecentData) {
                if (unViewAttached()) return;
                getView().getUserRecommendDatasSuccess(appRecentData);
            }
        });
    }
}
