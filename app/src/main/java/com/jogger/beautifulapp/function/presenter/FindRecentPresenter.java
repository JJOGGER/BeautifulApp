package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.function.contract.FindRecentContract;
import com.jogger.beautifulapp.function.model.FindRecentModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;


public class FindRecentPresenter extends BasePresenter<FindRecentContract.View,
        FindRecentContract.Model> implements FindRecentContract.Presenter {
    private long mPage = -1;
    private int mPageSize = 20;
    private boolean mHasNext;
    private long mLastPos;

    public boolean isHasNext() {
        return mHasNext;
    }

    @Override
    public void getRecentDatas() {
        mPage = -1;
        mModle.getRecentDatas(mPage, mPageSize, new OnHttpRequestListener<AppRecentData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("--------errorCode:" + errorCode);
            }

            @Override
            public void onSuccess(AppRecentData appData) {
                if (mView == null || appData == null || appData.getApps() == null) return;
                mHasNext = appData.getHas_next() == 1;
                mView.getRecentDatasSuccess(appData.getApps());
                mLastPos = appData.getApps().get(appData.getApps().size() - 1).getPos();
            }
        });
    }

    @Override
    public void getMoreDatas() {
        mPage = mLastPos;
        mModle.getRecentDatas(mPage, mPageSize, new OnHttpRequestListener<AppRecentData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("--------errorCode:" + errorCode);
                if (mView == null) return;
                mView.getMoreDatasFail();
            }

            @Override
            public void onSuccess(AppRecentData appData) {
                if (mView == null) return;
                mHasNext = appData.getHas_next() == 1;
                mView.getMoreDatasSuccess(appData.getApps());
            }
        });
    }

    @Override
    public FindRecentContract.Model attachModel() {
        return new FindRecentModel();
    }
}
