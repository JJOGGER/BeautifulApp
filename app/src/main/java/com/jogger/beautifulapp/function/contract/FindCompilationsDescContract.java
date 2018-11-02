package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.function.model.IDescBaseModel;
import com.jogger.beautifulapp.function.presenter.IDescBasePresenter;
import com.jogger.beautifulapp.function.ui.view.DescBaseView;

/**
 * Created by jogger on 2018/10/30.
 */
public interface FindCompilationsDescContract {
    interface Model extends IDescBaseModel {
    }

    interface View extends DescBaseView {
    }

    interface Presenter extends IDescBasePresenter<View, Model> {
    }
}
