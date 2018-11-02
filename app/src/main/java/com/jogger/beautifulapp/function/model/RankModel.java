package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppSocialArticleData;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.function.contract.RankContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class RankModel implements RankContract.Model {
    @Override
    public void getRankDatas(int page, int page_size, OnHttpRequestListener<AppSocialArticleData>
            listener) {
        HttpAction.getHttpAction().getRankDatas(page,page_size,listener);
    }

    @Override
    public void getRecentDescData(int id, OnHttpRequestListener<RecentAppData> listener) {
        HttpAction.getHttpAction().getRecentDescData(id,listener);
    }
}
