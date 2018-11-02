package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.function.contract.FindRecentContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class FindRecentModel implements FindRecentContract.Model {

    @Override
    public void getRecentDatas(long page, int pageSize, OnHttpRequestListener<AppRecentData>
            listener) {
        HttpAction.getHttpAction().getFindRecentDatas(page, pageSize, listener);
    }

}
