package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppCompilationDescData;
import com.jogger.beautifulapp.entity.AppCompilationsData;
import com.jogger.beautifulapp.function.contract.FindCompilationsContract;
import com.jogger.beautifulapp.function.model.FindCompilationsModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.T;

/**
 * Created by Jogger on 2018/6/14.
 */

public class FindCompilationsPresenter extends BasePresenter<FindCompilationsContract.View,
        FindCompilationsContract.Model> implements FindCompilationsContract.Presenter {
    private int mCurrentPage;
    private int mPageSize = 20;
    private boolean mHasNext;

    public boolean isHasNext() {
        return mHasNext;
    }

    @Override
    public FindCompilationsContract.Model attachModel() {
        return new FindCompilationsModel();
    }

    @Override
    public void getFindCompilationsDatas() {
        mCurrentPage = 1;
        getModel().getFindCompilationsDatas(mCurrentPage, mPageSize, new
                OnHttpRequestListener<AppCompilationsData>() {

                    @Override
                    public void onFailure(int errorCode) {

                    }

                    @Override
                    public void onSuccess(AppCompilationsData appCompilationsData) {
                        if (unViewAttached() || appCompilationsData == null || appCompilationsData
                                .getAlbums() == null)
                            return;
                        mHasNext = appCompilationsData.getHas_next() == 1;
                        getView().getFindCompilationsDatasSuccess(appCompilationsData.getAlbums());
                    }
                });
    }

    @Override
    public void getMoreDatas() {
        mCurrentPage++;
        getModel().getFindCompilationsDatas(mCurrentPage, mPageSize, new
                OnHttpRequestListener<AppCompilationsData>() {

                    @Override
                    public void onFailure(int errorCode) {
                        if (unViewAttached()) return;
                        getView().getMoreDatasFail();
                    }

                    @Override
                    public void onSuccess(AppCompilationsData appCompilationsData) {
                        if (unViewAttached() || appCompilationsData == null || appCompilationsData
                                .getAlbums() == null)
                            return;
                        mHasNext = appCompilationsData.getHas_next() == 1;
                        getView().getMoreDatasSuccess(appCompilationsData.getAlbums());
                    }
                });
    }

    @Override
    public void getCompilationDescDatas(int id) {
        getView().showLoadingWindow();
        getModel().getCompilationDescDatas(id, new OnHttpRequestListener<AppCompilationDescData>() {
            @Override
            public void onFailure(int errorCode) {
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
            }

            @Override
            public void onSuccess(AppCompilationDescData appCompilationDescData) {
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
                if (appCompilationDescData == null) {
                    T.show(R.string.request_failure);
                    return;
                }
                getView().getCompilationDescDatasSuccess(appCompilationDescData);
            }
        });
    }
}
