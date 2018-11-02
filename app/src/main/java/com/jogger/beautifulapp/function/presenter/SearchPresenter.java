package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppSearchData;
import com.jogger.beautifulapp.entity.Category;
import com.jogger.beautifulapp.entity.TagData;
import com.jogger.beautifulapp.function.contract.SearchContract;
import com.jogger.beautifulapp.function.model.SearchModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jogger on 2018/10/31.
 */
public class SearchPresenter extends DescBasePresenter<SearchContract.View, SearchContract.Model> implements SearchContract.Presenter {
    /**
     * 标签列表
     */
    @Override
    public void getSearchTags() {
        getModel().getSearchTags(new OnHttpRequestListener<TagData>() {
            @Override
            public void onFailure(int errorCode) {

            }

            @Override
            public void onSuccess(TagData tagData) {
                if (unViewAttached() || tagData == null) return;
                getView().getSearchTagsSuccess(tagData.getTags());
            }
        });
    }

    @Override
    public void search() {
        getView().showLoadingWindow();
        getModel().search(getView().getKeyword(), new OnHttpRequestListener<AppSearchData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("--------errorCode:" + errorCode);
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
            }

            @Override
            public void onSuccess(AppSearchData appSearchData) {
                if (unViewAttached() || appSearchData == null) return;
                getView().dismissLoadingWindow();
                if (appSearchData.getApps() == null && appSearchData.getArticles() == null)
                    return;
                List<Category> categories = new ArrayList<>();
                if (appSearchData.getArticles() != null && !appSearchData.getArticles().isEmpty()) {
                    Category category = new Category();
                    category.setTop_apps(appSearchData.getArticles());
                    if (Constant.TYPE_COMMUNITY.equals(appSearchData.getArticles().get(0).getType())) {
                        category.setTitle("发现应用");
                    } else {
                        category.setTitle("每日最美");
                    }
                    categories.add(category);
                }
                if (appSearchData.getApps() != null && !appSearchData.getApps().isEmpty()) {
                    Category category = new Category();
                    category.setTop_apps(appSearchData.getApps());
                    if (Constant.TYPE_COMMUNITY.equals(appSearchData.getApps().get(0).getType())) {
                        category.setTitle("发现应用");
                    } else {
                        category.setTitle("每日最美");
                    }
                    categories.add(category);
                }
                getView().searchSuccess(categories);
            }
        });
    }


    @Override
    public SearchContract.Model attachModel() {
        return new SearchModel();
    }
}
