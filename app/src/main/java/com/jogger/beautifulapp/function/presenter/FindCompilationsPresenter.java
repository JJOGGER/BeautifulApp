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

    @Override
    public FindCompilationsContract.Model attachModel() {
        return new FindCompilationsModel();
    }

    @Override
    public void getFindCompilationsDatas(int page, int page_size) {
        mModle.getFindCompilationsDatas(page, page_size, new
                OnHttpRequestListener<AppCompilationsData>() {

                    @Override
                    public void onFailure(int errorCode) {

                    }

                    @Override
                    public void onSuccess(AppCompilationsData appCompilationsData) {
                        if (mView == null) return;
                        mView.loadDatas(appCompilationsData);
                    }
                });
    }
}
