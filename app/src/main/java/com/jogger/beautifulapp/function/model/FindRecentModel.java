package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.function.contract.FindRecentContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by Jogger on 2018/6/13.
 */

public class FindRecentModel implements FindRecentContract.Model {
    @Override
    public void getRecentDatas(int page, int pageSize, OnHttpRequestListener<AppRecentData> listener) {
        HttpAction.getHttpAction().getFindRecentDatas(page, pageSize, listener);
    }
}
