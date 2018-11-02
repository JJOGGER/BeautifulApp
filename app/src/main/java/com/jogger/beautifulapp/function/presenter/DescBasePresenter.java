package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.function.model.IDescBaseModel;
import com.jogger.beautifulapp.function.ui.view.DescBaseView;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;

/**
 * Created by jogger on 2018/11/1.
 */
public abstract class DescBasePresenter<V extends DescBaseView, M extends IDescBaseModel> extends BasePresenter<V, M> implements IDescBasePresenter<V, M> {
    @Override
    public void getDescDatas(int id, int type) {
        getView().showLoadingWindow();
        if (type == Constant.TYPE_IMG_DESC) {
            getModel().getDesc1Datas(id, new OnHttpRequestListener<AppInfo>() {
                @Override
                public void onFailure(int errorCode) {
                    L.e("---------error1");
                    if (unViewAttached()) {
                        return;
                    }
                    getView().dismissLoadingWindow();
                }

                @Override
                public void onSuccess(AppInfo appInfo) {
                    L.e("---------appInfo");
                    if (unViewAttached() || appInfo == null) {
                        return;
                    }
                    getView().dismissLoadingWindow();
                    getView().getDesc1DatasSuccess(appInfo);
                }
            });
        } else {
            getModel().getDesc2Datas(id, new OnHttpRequestListener<RecentAppData>() {
                @Override
                public void onFailure(int errorCode) {
                    L.e("---------error2");
                    if (unViewAttached()) {
                        return;
                    }
                    getView().dismissLoadingWindow();
                }

                @Override
                public void onSuccess(RecentAppData recentAppData) {
                    if (unViewAttached() || recentAppData == null) {
                        return;
                    }
                    getView().dismissLoadingWindow();
                    getView().getDesc2DatasSuccess(recentAppData);
                }
            });
        }
    }
}
