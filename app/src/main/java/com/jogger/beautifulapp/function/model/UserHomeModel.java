package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.function.contract.UserHomeContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by Jogger on 2018/6/16.
 */

public class UserHomeModel implements UserHomeContract.Model {
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
