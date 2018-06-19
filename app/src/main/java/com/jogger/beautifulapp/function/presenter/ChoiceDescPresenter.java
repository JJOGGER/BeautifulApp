package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.function.contract.ChoiceDescContract;
import com.jogger.beautifulapp.function.model.ChoiceDescModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;

/**
 * Created by Jogger on 2018/6/18.
 */

public class ChoiceDescPresenter extends BasePresenter<ChoiceDescContract.View,
        ChoiceDescContract.Model> implements ChoiceDescContract.Presenter {

    @Override
    public ChoiceDescContract.Model attachModel() {
        return new ChoiceDescModel();
    }

    @Override
    public void getChoiceDescData(int id) {
        mModle.getChoiceDescData(id, new OnHttpRequestListener<AppInfo>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("---errorCode:" + errorCode);
            }

            @Override
            public void onSuccess(AppInfo appInfo) {
                if (mView == null) return;
                mView.getChoiceDescDataSuccess(appInfo);
            }
        });
    }
}
