package com.jogger.beautifulapp.function.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.entity.AppCategoryData;
import com.jogger.beautifulapp.function.adapter.CategoryAdapter;
import com.jogger.beautifulapp.function.contract.CategoryContract;
import com.jogger.beautifulapp.function.presenter.CategoryPresenter;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/10.精选分类
 */

public class CategoryFragment extends BaseFragment<CategoryPresenter> implements CategoryContract
        .View {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private CategoryAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected CategoryPresenter createPresenter() {
        return new CategoryPresenter();
    }

    @Override
    public void init() {
        mAdapter = new CategoryAdapter(null);
        rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        rvContent.setAdapter(mAdapter);
        mPresenter.getCategoryDatas();
    }

    @Override
    public void getCategoryDatasSuccess(AppCategoryData appCategoryData) {
        mAdapter.setNewData(appCategoryData.getApps());
    }
}
