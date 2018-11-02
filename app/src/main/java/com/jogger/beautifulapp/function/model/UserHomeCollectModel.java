package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppCollectData;
import com.jogger.beautifulapp.function.contract.UserHomeCollectContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class UserHomeCollectModel extends DescBaseModel implements UserHomeCollectContract.Model {
    private int mUserId;

    @Override
    public void setUserId(int userId) {
        mUserId = userId;
    }

    @Override
    public void getUserCollectDatas(int page, int page_size,
                                    OnHttpRequestListener<AppCollectData> listener) {
        HttpAction.getHttpAction().getUserCollectDatas(mUserId, page, page_size, listener);
    }
}
