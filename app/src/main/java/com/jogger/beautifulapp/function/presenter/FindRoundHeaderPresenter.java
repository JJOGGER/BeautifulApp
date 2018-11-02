package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.function.contract.FindRoundHeaderContract;
import com.jogger.beautifulapp.function.model.FindRoundHeaderModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.T;

/**
 * Created by jogger on 2018/10/29.
 */
public class FindRoundHeaderPresenter extends BasePresenter<FindRoundHeaderContract.View, FindRoundHeaderContract.Model> implements FindRoundHeaderContract.Presenter {
    @Override
    public void getHeaderDescDatas(int id) {
        getView().showLoadingWindow();
        getModel().getHeaderDescDatas(id, new OnHttpRequestListener<AppInfo>() {
            @Override
            public void onFailure(int errorCode) {
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
                getView().getHeaderDescDatasFail();
            }

            @Override
            public void onSuccess(AppInfo appInfo) {
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
                if (appInfo == null) {
                    T.show(R.string.request_failure);
                    return;
                }
                getView().getHeaderDescDatasSuccess(appInfo);
            }
        });
    }

    @Override
    public FindRoundHeaderContract.Model attachModel() {
        return new FindRoundHeaderModel();
    }
}
