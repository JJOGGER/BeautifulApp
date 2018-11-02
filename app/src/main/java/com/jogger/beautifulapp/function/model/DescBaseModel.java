package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by jogger on 2018/11/1.进入详情有2种
 */
public class DescBaseModel implements IDescBaseModel {
    @Override
    public void getDesc1Datas(int id, OnHttpRequestListener<AppInfo> listener) {
        HttpAction.getHttpAction().getChoiceDescData(id, listener);
    }

    @Override
    public void getDesc2Datas(int id, OnHttpRequestListener<RecentAppData> listener) {
        HttpAction.getHttpAction().getRecentDescData(id, listener);
    }
}
