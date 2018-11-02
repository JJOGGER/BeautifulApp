package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppCategoryData;
import com.jogger.beautifulapp.function.contract.CategoryContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;


public class CategoryModel extends DescBaseModel implements CategoryContract.Model {
    @Override
    public void getCategoryDatas(OnHttpRequestListener<AppCategoryData> listener) {
        HttpAction.getHttpAction().getCategoryDatas(listener);
    }
}
