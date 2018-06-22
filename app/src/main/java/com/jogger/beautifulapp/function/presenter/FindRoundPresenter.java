package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppMediaArticleData;
import com.jogger.beautifulapp.function.contract.FindRoundContract;
import com.jogger.beautifulapp.function.model.FindRoundModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public class FindRoundPresenter extends BasePresenter<FindRoundContract.View, FindRoundContract
        .Model> implements FindRoundContract.Presenter {
    private int mPage, mPageSize = 20;
    private boolean mHasNext;

    public boolean isHasNext() {
        return mHasNext;
    }

    @Override
    public void getFindRoundTopDatas() {
        mModle.getFindRoundTopDatas(new OnHttpRequestListener<AppMediaArticleData>() {
            @Override
            public void onFailure(int errorCode) {

            }

            @Override
            public void onSuccess(AppMediaArticleData appMediaArticleData) {
                if (mView == null || appMediaArticleData == null || appMediaArticleData
                        .getMedia_articles() == null)
                    return;
                mView.getFindRoundTopDatasSuccess(appMediaArticleData.getMedia_articles());
            }
        });
    }

    @Override
    public void getFindRoundDatas() {
        mPage = 1;
        mModle.getFindRoundDatas(mPage, mPageSize, new OnHttpRequestListener<AppMediaArticleData>
                () {
            @Override
            public void onFailure(int errorCode) {
            }

            @Override
            public void onSuccess(AppMediaArticleData appMediaArticleData) {
                if (mView == null || appMediaArticleData == null || appMediaArticleData
                        .getMedia_articles() == null)
                    return;
                mHasNext = appMediaArticleData.getHas_next() == 1;
                mView.getFindRoundDatasSuccess(appMediaArticleData.getMedia_articles());
            }
        });
    }

    @Override
    public void getMoreDatas() {
        mPage++;
        mModle.getFindRoundDatas(mPage, mPageSize, new OnHttpRequestListener<AppMediaArticleData>
                () {
            @Override
            public void onFailure(int errorCode) {
                if (mView == null) return;
                mView.getMoreDatasFail();
            }

            @Override
            public void onSuccess(AppMediaArticleData appMediaArticleData) {
                if (mView == null || appMediaArticleData == null || appMediaArticleData
                        .getMedia_articles() == null)
                    return;
                mHasNext = appMediaArticleData.getHas_next() == 1;
                mView.getFindRoundDatasSuccess(appMediaArticleData.getMedia_articles());
            }
        });
    }

    @Override
    public FindRoundContract.Model attachModel() {
        return new FindRoundModel();
    }
}
