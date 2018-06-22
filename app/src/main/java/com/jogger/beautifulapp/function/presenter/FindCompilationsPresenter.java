package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppCompilationsData;
import com.jogger.beautifulapp.function.contract.FindCompilationsContract;
import com.jogger.beautifulapp.function.model.FindCompilationsModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

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
        mModle.getFindCompilationsDatas(mCurrentPage, mPageSize, new
                OnHttpRequestListener<AppCompilationsData>() {

                    @Override
                    public void onFailure(int errorCode) {

                    }

                    @Override
                    public void onSuccess(AppCompilationsData appCompilationsData) {
                        if (mView == null || appCompilationsData == null || appCompilationsData
                                .getAlbums() == null)
                            return;
                        mHasNext = appCompilationsData.getHas_next() == 1;
                        mView.getFindCompilationsDatasSuccess(appCompilationsData.getAlbums());
                    }
                });
    }

    @Override
    public void getMoreDatas() {
        mCurrentPage++;
        mModle.getFindCompilationsDatas(mCurrentPage, mPageSize, new
                OnHttpRequestListener<AppCompilationsData>() {

                    @Override
                    public void onFailure(int errorCode) {
                        if (mView == null) return;
                        mView.getMoreDatasFail();
                    }

                    @Override
                    public void onSuccess(AppCompilationsData appCompilationsData) {
                        if (mView == null || appCompilationsData == null || appCompilationsData
                                .getAlbums() == null)
                            return;
                        mHasNext = appCompilationsData.getHas_next() == 1;
                        mView.getMoreDatasSuccess(appCompilationsData.getAlbums());
                    }
                });
    }
}
