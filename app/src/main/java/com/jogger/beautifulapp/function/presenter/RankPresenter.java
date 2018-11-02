package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppSocialArticleData;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.function.contract.RankContract;
import com.jogger.beautifulapp.function.model.RankModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;
import com.jogger.beautifulapp.util.T;


public class RankPresenter extends BasePresenter<RankContract.View, RankContract.Model>
        implements RankContract.Presenter {

    @Override
    public RankContract.Model attachModel() {
        return new RankModel();
    }

    @Override
    public void getRankDatas(int page, int page_size) {
        getModel().getRankDatas(page, page_size, new OnHttpRequestListener<AppSocialArticleData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("--------errorcode" + errorCode);
            }

            @Override
            public void onSuccess(AppSocialArticleData appSocialArticleData) {
                if (unViewAttached()) return;
                getView().getRankDatasSuccess(appSocialArticleData);
            }
        });
    }

    @Override
    public void getRecentDescData(int id) {
        getView().showLoadingWindow();
        getModel().getRecentDescData(id, new OnHttpRequestListener<RecentAppData>() {
            @Override
            public void onFailure(int errorCode) {
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
            }

            @Override
            public void onSuccess(RecentAppData recentAppData) {
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
                if (recentAppData == null) {
                    T.show(R.string.request_failure);
                    return;
                }
                getView().getRecentDescDataSuccess(recentAppData);
            }
        });
    }
}
