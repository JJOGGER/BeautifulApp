package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by jogger on 2018/11/1.
 */
public interface IDescBaseModel extends BaseModel {
    void getDesc1Datas(int id, OnHttpRequestListener<AppInfo> listener);

    void getDesc2Datas(int id, OnHttpRequestListener<RecentAppData> listener);
}
