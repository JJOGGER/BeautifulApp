package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.entity.AppCategoryMoreData;
import com.jogger.beautifulapp.function.contract.CategoryMoreContract;
import com.jogger.beautifulapp.function.model.CategoryMoreModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;

/**
 * Created by jogger on 2018/11/1.
 */
public class CategoryMorePresenter extends DescBasePresenter<CategoryMoreContract.View, CategoryMoreContract.Model> implements CategoryMoreContract.Presenter {
    private int mPage;
    private int mPageSize = 20;
    private boolean mHasNext;
    private int mID;

    public CategoryMorePresenter(int id) {
        mID = id;
    }

    public boolean isHasNext() {
        return mHasNext;
    }

    @Override
    public void getCategoryDatas() {
        mPage = 1;
        getModel().getCategoryDatas(mID, mPage, mPageSize, new OnHttpRequestListener<AppCategoryMoreData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("--------errorCode:" + errorCode);
            }

            @Override
            public void onSuccess(AppCategoryMoreData categoryMoreData) {
                L.e("----categoryMoreData:"+categoryMoreData);
                if (unViewAttached() || categoryMoreData == null || categoryMoreData.getApps() == null)
                    return;
                mHasNext = categoryMoreData.getHas_next() == 1;
                getView().getCategoryDatasSuccess(categoryMoreData.getApps());
            }
        });
    }


    @Override
    public void getMoreDatas() {
        mPage++;
        getModel().getCategoryDatas(mID, mPage, mPageSize, new
                OnHttpRequestListener<AppCategoryMoreData>() {

                    @Override
                    public void onFailure(int errorCode) {
                        if (unViewAttached()) return;
                        getView().getMoreDatasFail();
                    }

                    @Override
                    public void onSuccess(AppCategoryMoreData categoryMoreData) {
                        if (unViewAttached() || categoryMoreData == null || categoryMoreData.getApps() == null) {
                            return;
                        }
                        mHasNext = categoryMoreData.getHas_next() == 1;
                        getView().getMoreDatasSuccess(categoryMoreData.getApps());
                    }
                });
    }

    @Override
    public CategoryMoreContract.Model attachModel() {
        return new CategoryMoreModel();
    }
}
