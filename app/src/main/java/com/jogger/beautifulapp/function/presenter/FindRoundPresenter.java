package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppMediaArticleData;
import com.jogger.beautifulapp.function.contract.FindRoundContract;
import com.jogger.beautifulapp.function.model.FindRoundModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

public class FindRoundPresenter extends BasePresenter<FindRoundContract.View, FindRoundContract
        .Model> implements FindRoundContract.Presenter {
    @Override
    public void getFindRoundTopDatas() {
        mModle.getFindRoundTopDatas(new OnHttpRequestListener<AppMediaArticleData>() {
            @Override
            public void onFailure(int errorCode) {

            }

            @Override
            public void onSuccess(AppMediaArticleData appMediaArticleData) {
                if (mView == null) return;
                mView.loadTopDatas(appMediaArticleData);
            }
        });
    }

    @Override
    public void getFindRoundDatas(int page, int page_size) {
        mModle.getFindRoundDatas(page, page_size, new OnHttpRequestListener<AppMediaArticleData>() {
            @Override
            public void onFailure(int errorCode) {

            }

            @Override
            public void onSuccess(AppMediaArticleData appMediaArticleData) {
                if (mView == null) return;
                mView.loadDatas(appMediaArticleData);
            }
        });
    }

    @Override
    public FindRoundContract.Model attachModel() {
        return new FindRoundModel();
    }
}
