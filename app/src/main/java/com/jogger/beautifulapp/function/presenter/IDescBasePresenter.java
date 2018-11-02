package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.function.model.IDescBaseModel;
import com.jogger.beautifulapp.function.ui.view.DescBaseView;

/**
 * Created by jogger on 2018/11/1.
 */
public interface IDescBasePresenter<V extends DescBaseView,M extends IDescBaseModel> extends IPresenter<V ,M > {
    void getDescDatas(int id, int type);
}
