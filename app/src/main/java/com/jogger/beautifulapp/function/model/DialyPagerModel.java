package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.function.contract.DialyPagerContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public class DialyPagerModel implements DialyPagerContract.Model {

    @Override
    public void getChoiceDescData(int id, OnHttpRequestListener<AppInfo> listener) {
        HttpAction.getHttpAction().getChoiceDescData(id, listener);
    }
}
