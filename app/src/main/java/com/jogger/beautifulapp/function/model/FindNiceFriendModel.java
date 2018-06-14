package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppNiceFriendData;
import com.jogger.beautifulapp.function.contract.FindNiceFriendContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public class FindNiceFriendModel implements FindNiceFriendContract.Model {
    @Override
    public void getFindNiceFriendDatas(OnHttpRequestListener<AppNiceFriendData> listener) {
        HttpAction.getHttpAction().getFindNiceFriendDatas(listener);
    }
}
