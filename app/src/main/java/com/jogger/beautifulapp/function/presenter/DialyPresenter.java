package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppData;
import com.jogger.beautifulapp.function.contract.DialyContract;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;


public class DialyPresenter extends BasePresenter<DialyContract.View, DialyContract.Model>
        implements DialyContract.Presenter {
    public DialyPresenter(DialyContract.Model modle) {
        super(modle);
    }

    @Override
    public void getDialyDatas(int page, int pageSize) {
        mModle.getDialyDatas(page, pageSize, new OnHttpRequestListener<AppData>() {
            @Override
            public void onFailure(int errorCode) {

            }

            @Override
            public void onSuccess(AppData appData) {
                L.e("---------appdata:" + appData);
                mView.loadDatas(appData);
            }
        });
    }
}
