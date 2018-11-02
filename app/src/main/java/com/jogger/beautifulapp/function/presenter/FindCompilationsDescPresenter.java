package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.function.contract.FindCompilationsDescContract;
import com.jogger.beautifulapp.function.model.FindCompilationsDescModel;

/**
 * Created by jogger on 2018/10/30.
 */
public class FindCompilationsDescPresenter extends DescBasePresenter<FindCompilationsDescContract.View, FindCompilationsDescContract.Model> implements FindCompilationsDescContract.Presenter {

    @Override
    public FindCompilationsDescContract.Model attachModel() {
        return new FindCompilationsDescModel();
    }
}
