package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppInfoData;
import com.jogger.beautifulapp.function.contract.DialyContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class DialyModel implements DialyContract.Model {

    @Override
    public void getDialyDatas(int page, int pageSize, OnHttpRequestListener<AppInfoData> listener) {
        HttpAction.getHttpAction().getDialyDatas(page, pageSize, listener);
    }
}
