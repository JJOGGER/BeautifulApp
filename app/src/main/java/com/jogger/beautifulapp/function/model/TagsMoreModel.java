package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.function.contract.TagsMoreContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class TagsMoreModel implements TagsMoreContract.Model {

    @Override
    public void getTagsMoreData(int id, int page, int pageSize, OnHttpRequestListener<AppRecentData> listener) {
        HttpAction.getHttpAction().getTagsMoreData(id, page, pageSize, listener);
    }
}
