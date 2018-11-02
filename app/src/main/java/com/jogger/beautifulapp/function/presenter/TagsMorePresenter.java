package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.function.contract.TagsMoreContract;
import com.jogger.beautifulapp.function.model.TagsMoreModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;


public class TagsMorePresenter extends BasePresenter<TagsMoreContract.View,
        TagsMoreContract.Model> implements TagsMoreContract.Presenter {
    private int mPage = 1;
    private int mPageSize = 20;
    private boolean mHasNext;
    private int mID;

    public TagsMorePresenter(int id) {
        mID = id;
    }

    public boolean isHasNext() {
        return mHasNext;
    }

    @Override
    public void getTagsDatas(final boolean isRefresh) {
        if (!isRefresh)
            getView().showLoadingWindow();
        mPage = 1;
        getModel().getTagsMoreData(mID, mPage, mPageSize, new OnHttpRequestListener<AppRecentData>() {
            @Override
            public void onFailure(int errorCode) {
                if (unViewAttached()) return;
                if (!isRefresh)
                    getView().dismissLoadingWindow();
            }

            @Override
            public void onSuccess(AppRecentData appData) {
                if (unViewAttached() || appData == null || appData.getApps() == null) return;
                if (!isRefresh)
                    getView().dismissLoadingWindow();
                mHasNext = appData.getHas_next() == 1;
                getView().getTagsDataSuccess(appData.getApps());
            }
        });
    }

    @Override
    public void getMoreDatas() {
        mPage++;
        getModel().getTagsMoreData(mID, mPage, mPageSize, new OnHttpRequestListener<AppRecentData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("--------errorCode:" + errorCode);
                if (unViewAttached()) return;
                getView().getMoreDatasFail();
            }

            @Override
            public void onSuccess(AppRecentData appData) {
                if (unViewAttached()) return;
                mHasNext = appData.getHas_next() == 1;
                getView().getMoreDatasSuccess(appData.getApps());
            }
        });
    }

    @Override
    public TagsMoreContract.Model attachModel() {
        return new TagsMoreModel();
    }

}
