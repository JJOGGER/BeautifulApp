package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.function.contract.FindRecentContract;
import com.jogger.beautifulapp.function.model.FindRecentModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;


public class FindRecentPresenter extends BasePresenter<FindRecentContract.View,
        FindRecentContract.Model> implements FindRecentContract.Presenter {
    public FindRecentPresenter() {
        attachModel(new FindRecentModel());
    }

    @Override
    public void getRecentDatas(int page, int pageSize) {
        mModle.getRecentDatas(page, pageSize, new OnHttpRequestListener<AppRecentData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("--------errorCode:"+errorCode);
            }

            @Override
            public void onSuccess(AppRecentData appData) {
                if (mView == null) return;
                mView.loadDatas(appData);
            }
        });
    }
}
