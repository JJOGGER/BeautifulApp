package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.AppMediaArticleData;
import com.jogger.beautifulapp.function.contract.FindRoundContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class FindRoundModel implements FindRoundContract.Model {
    @Override
    public void getFindRoundTopDatas(OnHttpRequestListener<AppMediaArticleData> listener) {
        HttpAction.getHttpAction().getFindRoundTopDatas(listener);
    }

    @Override
    public void getFindRoundDatas(int page, int page_size, OnHttpRequestListener<AppMediaArticleData>
            listener) {
        HttpAction.getHttpAction().getFindRoundDatas(page, page_size, listener);
    }

    @Override
    public void getDescDatas(int id, OnHttpRequestListener<AppInfo> listener) {
        HttpAction.getHttpAction().getChoiceDescData(id, listener);
    }
}
