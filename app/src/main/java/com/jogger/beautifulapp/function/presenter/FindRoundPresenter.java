package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.AppMediaArticleData;
import com.jogger.beautifulapp.function.contract.FindRoundContract;
import com.jogger.beautifulapp.function.model.FindRoundModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.T;

public class FindRoundPresenter extends BasePresenter<FindRoundContract.View, FindRoundContract
        .Model> implements FindRoundContract.Presenter {
    private int mPage, mPageSize = 20;
    private boolean mHasNext;

    public boolean isHasNext() {
        return mHasNext;
    }

    @Override
    public void getFindRoundTopDatas() {
        getModel().getFindRoundTopDatas(new OnHttpRequestListener<AppMediaArticleData>() {
            @Override
            public void onFailure(int errorCode) {

            }

            @Override
            public void onSuccess(AppMediaArticleData appMediaArticleData) {
                if (unViewAttached() || appMediaArticleData == null || appMediaArticleData
                        .getMedia_articles() == null)
                    return;
                getView().getFindRoundTopDatasSuccess(appMediaArticleData.getMedia_articles());
            }
        });
    }

    @Override
    public void getFindRoundDatas() {
        mPage = 1;
        getModel().getFindRoundDatas(mPage, mPageSize, new OnHttpRequestListener<AppMediaArticleData>
                () {
            @Override
            public void onFailure(int errorCode) {
            }

            @Override
            public void onSuccess(AppMediaArticleData appMediaArticleData) {
                if (unViewAttached() || appMediaArticleData == null || appMediaArticleData
                        .getMedia_articles() == null)
                    return;
                mHasNext = appMediaArticleData.getHas_next() == 1;
                getView().getFindRoundDatasSuccess(appMediaArticleData.getMedia_articles());
            }
        });
    }

    @Override
    public void getMoreDatas() {
        mPage++;
        getModel().getFindRoundDatas(mPage, mPageSize, new OnHttpRequestListener<AppMediaArticleData>
                () {
            @Override
            public void onFailure(int errorCode) {
                if (unViewAttached()) return;
                getView().getMoreDatasFail();
            }

            @Override
            public void onSuccess(AppMediaArticleData appMediaArticleData) {
                if (unViewAttached() || appMediaArticleData == null || appMediaArticleData
                        .getMedia_articles() == null)
                    return;
                mHasNext = appMediaArticleData.getHas_next() == 1;
                getView().getMoreDatasSuccess(appMediaArticleData.getMedia_articles());
            }
        });
    }

    @Override
    public void getDescDatas(int id) {
        getView().showLoadingWindow();
        getModel().getDescDatas(id, new OnHttpRequestListener<AppInfo>() {
            @Override
            public void onFailure(int errorCode) {
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
                getView().getDescDatasFail();
            }

            @Override
            public void onSuccess(AppInfo info) {
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
                if (info==null){
                    T.show(R.string.request_failure);
                    return;
                }
                getView().getDescDatasSuccess(info);
            }
        });
    }

    @Override
    public FindRoundContract.Model attachModel() {
        return new FindRoundModel();
    }
}
