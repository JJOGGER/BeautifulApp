package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppNiceFriendData;
import com.jogger.beautifulapp.function.contract.FindNiceFriendContract;
import com.jogger.beautifulapp.function.model.FindNiceFriendModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;


public class FindNiceFriendPresenter extends BasePresenter<FindNiceFriendContract.View,
        FindNiceFriendContract.Model> implements FindNiceFriendContract.Presenter {

    @Override
    public FindNiceFriendContract.Model attachModel() {
        return new FindNiceFriendModel();
    }

    @Override
    public void getFindNickFriendDatas() {
        getModel().getFindNiceFriendDatas(new OnHttpRequestListener<AppNiceFriendData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("----errcode:"+errorCode);
            }

            @Override
            public void onSuccess(AppNiceFriendData appNiceFriendData) {
                if (unViewAttached()) return;
                getView().getFindNiceFriendDatasSuccess(appNiceFriendData.getUsers_rank());
            }
        });
    }

}
