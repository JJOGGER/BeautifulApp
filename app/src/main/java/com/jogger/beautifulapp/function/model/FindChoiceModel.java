package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.FindChoiceData;
import com.jogger.beautifulapp.function.contract.FindChoiceContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public class FindChoiceModel implements FindChoiceContract.Model {
    @Override
    public void getFindChoiceDatas(String startDate, OnHttpRequestListener<FindChoiceData> listener) {
        HttpAction.getHttpAction().getFindChoiceDatas(startDate, listener);
    }

    @Override
    public void getChoiceDescData(int id, OnHttpRequestListener<AppInfo> listener) {
        HttpAction.getHttpAction().getChoiceDescData(id, listener);
    }
}
