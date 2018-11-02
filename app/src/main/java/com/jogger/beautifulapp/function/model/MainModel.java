package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.function.contract.MainContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class MainModel implements MainContract.Model {
    @Override
    public void getRandomDatas(OnHttpRequestListener<AppRecentData> listener) {
        HttpAction.getHttpAction().getRandomDatas(listener);
    }
}
