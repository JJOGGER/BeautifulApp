package com.jogger.beautifulapp.function.ui.fragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.base.recyclerview.refresh.RefreshRecyclerView;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.function.adapter.FindRecentAdapter;
import com.jogger.beautifulapp.function.contract.FindRecentContract;
import com.jogger.beautifulapp.function.presenter.FindRecentPresenter;
import com.jogger.beautifulapp.function.ui.activity.RecentDescActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/13.发现最新页
 */

public class FindRecentFragment extends BaseFragment<FindRecentPresenter> implements
        FindRecentContract.View, RefreshRecyclerView.OnRefreshListener, BaseQuickAdapter
        .RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_content)
    RefreshRecyclerView rvContent;
    private FindRecentAdapter mAdapter;
    private boolean mIsLoading;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_item;
    }

    @Override
    public void init() {
        rvContent.setLayoutManager(new MyLinearLayoutManager(mActivity));
        mAdapter = new FindRecentAdapter(null);
        rvContent.setAdapter(mAdapter);
        rvContent.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnLoadMoreListener(this, rvContent);
    }

    @Override
    protected FindRecentPresenter createPresenter() {
        return new FindRecentPresenter();
    }


    @Override
    public void loadData() {
        mPresenter.getRecentDatas();
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void getRecentDatasSuccess(List<RecentAppData> recentAppDatas) {
        mAdapter.setNewData(recentAppDatas);
        rvContent.onStopRefresh();
    }

    @Override
    public void getMoreDatasSuccess(List<RecentAppData> recentAppDatas) {
        mAdapter.addData(recentAppDatas);
        mIsLoading = false;
        mAdapter.loadMoreComplete();
    }

    @Override
    public void getMoreDatasFail() {
        mAdapter.loadMoreFail();
        mIsLoading = false;
    }

    @Override
    public void onLoadMoreRequested() {
        if (mIsLoading)
            return;
        mIsLoading = true;
        rvContent.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mPresenter.isHasNext())
                    mPresenter.getMoreDatas();
                else mAdapter.loadMoreEnd();
            }
        }, 100);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        RecentAppData recentAppData = (RecentAppData) adapter.getItem(position);
        if (recentAppData == null) return;
        startNewActivity(RecentDescActivity.class, Constant.APP_DATA, recentAppData);
    }
}
