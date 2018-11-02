package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.function.contract.ChoiceDescContract;
import com.jogger.beautifulapp.function.model.ChoiceDescModel;

/**
 * Created by Jogger on 2018/6/18.
 */

public class ChoiceDescPresenter extends BasePresenter<ChoiceDescContract.View,
        ChoiceDescContract.Model> implements ChoiceDescContract.Presenter {

    @Override
    public ChoiceDescContract.Model attachModel() {
        return new ChoiceDescModel();
    }

}
