package com.jogger.beautifulapp.function.contract;

import com.jogger.beautifulapp.entity.AppSearchData;
import com.jogger.beautifulapp.entity.Category;
import com.jogger.beautifulapp.entity.Tag;
import com.jogger.beautifulapp.entity.TagData;
import com.jogger.beautifulapp.function.model.IDescBaseModel;
import com.jogger.beautifulapp.function.presenter.IDescBasePresenter;
import com.jogger.beautifulapp.function.ui.view.DescBaseView;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

import java.util.List;

/**
 * Created by jogger on 2018/10/31.
 */
public interface SearchContract {
    interface Model extends IDescBaseModel {
        void search(String keyword, OnHttpRequestListener<AppSearchData> listener);

        void getSearchTags(OnHttpRequestListener<TagData> listener);

    }

    interface View extends DescBaseView {
        String getKeyword();

        void getSearchTagsSuccess(List<Tag> tags);

        void searchSuccess(List<Category> appSearchData);

    }

    interface Presenter extends IDescBasePresenter<View, Model> {
        void getSearchTags();

        void search();
    }
}
