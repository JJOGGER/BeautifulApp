package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.AppSearchData;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.entity.TagData;
import com.jogger.beautifulapp.function.contract.SearchContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by jogger on 2018/10/31.
 */
public class SearchModel implements SearchContract.Model {
    @Override
    public void search(String keyword, OnHttpRequestListener<AppSearchData> listener) {
        HttpAction.getHttpAction().getSearchs(keyword, listener);
    }

    @Override
    public void getSearchTags(OnHttpRequestListener<TagData> listener) {
        HttpAction.getHttpAction().getSearchTags(listener);
    }
    @Override
    public void getDesc1Datas(int id, OnHttpRequestListener<AppInfo> listener) {
        HttpAction.getHttpAction().getChoiceDescData(id, listener);
    }

    @Override
    public void getDesc2Datas(int id, OnHttpRequestListener<RecentAppData> listener) {
        HttpAction.getHttpAction().getRecentDescData(id, listener);
    }
}
