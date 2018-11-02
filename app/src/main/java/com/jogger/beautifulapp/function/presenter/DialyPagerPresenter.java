package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.function.contract.DialyPagerContract;
import com.jogger.beautifulapp.function.model.DialyPagerModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.T;


public class DialyPagerPresenter extends BasePresenter<DialyPagerContract.View,
        DialyPagerContract.Model> implements DialyPagerContract.Presenter {


    @Override
    public DialyPagerContract.Model attachModel() {
        return new DialyPagerModel();
    }

    @Override
    public void getChoiceDescData(int id) {
        getView().showLoadingWindow();
        getModel().getChoiceDescData(id, new OnHttpRequestListener<AppInfo>() {
            @Override
            public void onFailure(int errorCode) {
                if (unViewAttached()) return;
                T.show(R.string.request_failure);
                getView().dismissLoadingWindow();
            }

            @Override
            public void onSuccess(AppInfo appInfo) {
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
                if (appInfo==null){
                    T.show(R.string.request_failure);
                    return;
                }
                getView().getChoiceDescDataSuccess(appInfo);
            }
        });
    }
}
