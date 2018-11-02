package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.UserHomeInfo;
import com.jogger.beautifulapp.function.contract.UserHomeContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by Jogger on 2018/6/16.
 */

public class UserHomeModel implements UserHomeContract.Model {

    @Override
    public void getUserHomeInfo(int id, OnHttpRequestListener<UserHomeInfo> listener) {
        HttpAction.getHttpAction().getUserHomeInfo(id, listener);
    }
}
