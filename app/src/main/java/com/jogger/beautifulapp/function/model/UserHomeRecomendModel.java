package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.function.contract.UserHomeRecomendContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class UserHomeRecomendModel implements UserHomeRecomendContract.Model {
    private int mUserId;

    @Override
    public void setUserId(int userId) {
        mUserId = userId;
    }

    @Override
    public void getUserRecommendDatas(int page, int page_size,
                                      OnHttpRequestListener<AppRecentData> listener) {
        HttpAction.getHttpAction().getUserRecommendDatas(mUserId, page, page_size, listener);
    }
}
