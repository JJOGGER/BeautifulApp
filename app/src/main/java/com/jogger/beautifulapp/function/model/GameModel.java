package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppInfoData;
import com.jogger.beautifulapp.function.contract.GameContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class GameModel implements GameContract.Model {
    @Override
    public void getGameDatas(int page, int pageSize, OnHttpRequestListener<AppInfoData> listener) {
        HttpAction.getHttpAction().getGameDatas(page, pageSize, listener);
    }
}
