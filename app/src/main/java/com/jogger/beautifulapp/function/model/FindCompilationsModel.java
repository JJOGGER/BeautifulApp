package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppCompilationDescData;
import com.jogger.beautifulapp.entity.AppCompilationsData;
import com.jogger.beautifulapp.function.contract.FindCompilationsContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class FindCompilationsModel implements FindCompilationsContract.Model {

    @Override
    public void getFindCompilationsDatas(int page, int page_size,
                                         OnHttpRequestListener<AppCompilationsData> listener) {
        HttpAction.getHttpAction().getFindCompilationsDatas(page, page_size, listener);
    }

    @Override
    public void getCompilationDescDatas(int id, OnHttpRequestListener<AppCompilationDescData> listener) {
        HttpAction.getHttpAction().getCompilationDescDatas(id, listener);
    }
}
