package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.function.contract.FindRoundHeaderContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by jogger on 2018/10/29.
 */
public class FindRoundHeaderModel implements FindRoundHeaderContract.Model {
    @Override
    public void getHeaderDescDatas(int id, OnHttpRequestListener<AppInfo> listener) {
        HttpAction.getHttpAction().getChoiceDescData(id, listener);
    }
}
