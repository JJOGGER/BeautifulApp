package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppSocialArticleData;
import com.jogger.beautifulapp.function.contract.RankContract;
import com.jogger.beautifulapp.function.model.RankModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;


public class RankPresenter extends BasePresenter<RankContract.View, RankContract.Model>
        implements RankContract.Presenter {

    @Override
    public RankContract.Model attachModel() {
        return new RankModel();
    }

    @Override
    public void getRankDatas(int page, int page_size) {
        mModle.getRankDatas(page, page_size, new OnHttpRequestListener<AppSocialArticleData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("--------errorcode" + errorCode);
            }

            @Override
            public void onSuccess(AppSocialArticleData appSocialArticleData) {
                if (mView == null) return;
                mView.getRankDatasSuccess(appSocialArticleData);
            }
        });
    }
}
