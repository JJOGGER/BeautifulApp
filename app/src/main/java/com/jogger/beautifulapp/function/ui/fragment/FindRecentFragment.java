package com.jogger.beautifulapp.function.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.function.adapter.FindRecentAdapter;
import com.jogger.beautifulapp.function.contract.FindRecentContract;
import com.jogger.beautifulapp.function.presenter.FindRecentPresenter;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/13.发现最新页
 */

public class FindRecentFragment extends BaseFragment<FindRecentPresenter> implements
        FindRecentContract.View {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private FindRecentAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_item;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new FindRecentPresenter();
    }

    @Override
    public void init() {
        rvContent.setLayoutManager(new MyLinearLayoutManager(mActivity));
        mAdapter = new FindRecentAdapter(null);
        rvContent.setAdapter(mAdapter);
        mPresenter.getRecentDatas(-1, 20);
    }

    @Override
    public void loadDatas(AppRecentData appData) {
        mAdapter.setNewData(appData.getApps());
    }
}
