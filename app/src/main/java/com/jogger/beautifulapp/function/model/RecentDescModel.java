package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.function.contract.RecentDescContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by Jogger on 2018/6/24.
 */

public class RecentDescModel implements RecentDescContract.Model {
    @Override
    public void getDescData(int id, OnHttpRequestListener<RecentAppData> listener) {
        HttpAction.getHttpAction().getRecentDescData(id, listener);
    }
}
