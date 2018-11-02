package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.function.contract.RecentDescContract;
import com.jogger.beautifulapp.function.model.RecentDescModel;

/**
 * Created by Jogger on 2018/6/24.
 */

public class RecentDescPresenter extends BasePresenter<RecentDescContract.View,
        RecentDescContract.Model> implements RecentDescContract.Presenter {

    @Override
    public RecentDescContract.Model attachModel() {
        return new RecentDescModel();
    }

}
