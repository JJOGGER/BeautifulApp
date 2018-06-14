package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.FindChoiceData;
import com.jogger.beautifulapp.function.contract.FindChoiceContract;
import com.jogger.beautifulapp.function.model.FindChoiceModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class FindChoicePresenter extends BasePresenter<FindChoiceContract.View,
        FindChoiceContract.Model> implements FindChoiceContract.Presenter {

    @Override
    public void getFindChoiceDatas(String startDate) {
        mModle.getFindChoiceDatas(startDate, new OnHttpRequestListener<FindChoiceData>() {
            @Override
            public void onFailure(int errorCode) {

            }

            @Override
            public void onSuccess(FindChoiceData appData) {
                if (mView == null) return;
                mView.loadDatas(appData);
            }
        });
    }

    @Override
    public FindChoiceContract.Model attachModel() {
        return new FindChoiceModel();
    }
}
