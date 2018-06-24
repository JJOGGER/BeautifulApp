package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.function.contract.RecentDescContract;
import com.jogger.beautifulapp.function.model.RecentDescModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;

/**
 * Created by Jogger on 2018/6/24.
 */

public class RecentDescPresenter extends BasePresenter<RecentDescContract.View,
        RecentDescContract.Model> implements RecentDescContract.Presenter {

    @Override
    public RecentDescContract.Model attachModel() {
        return new RecentDescModel();
    }

    @Override
    public void getDescData(int id) {
        mModle.getDescData(id, new OnHttpRequestListener<RecentAppData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("---errorCode:" + errorCode);
            }

            @Override
            public void onSuccess(RecentAppData appData) {
                if (mView == null) return;
                mView.getDescDataSuccess(appData);
            }
        });
    }
}
