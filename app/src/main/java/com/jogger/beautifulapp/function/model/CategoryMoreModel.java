package com.jogger.beautifulapp.function.model;

import com.jogger.beautifulapp.entity.AppCategoryMoreData;
import com.jogger.beautifulapp.function.contract.CategoryMoreContract;
import com.jogger.beautifulapp.http.HttpAction;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by jogger on 2018/11/1.
 */
public class CategoryMoreModel extends DescBaseModel implements CategoryMoreContract.Model {
    @Override
    public void getCategoryDatas(int id, int page, int pageSize, OnHttpRequestListener<AppCategoryMoreData> listener) {
        HttpAction.getHttpAction().getCategoryMoreDatas(id, page, pageSize, listener);
    }
}
