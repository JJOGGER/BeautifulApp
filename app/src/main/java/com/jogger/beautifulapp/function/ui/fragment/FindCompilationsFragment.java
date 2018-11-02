package com.jogger.beautifulapp.function.ui.fragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyGridLayoutManager;
import com.jogger.beautifulapp.base.recyclerview.SpaceItemDecoration;
import com.jogger.beautifulapp.base.recyclerview.refresh.RefreshRecyclerView;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.Album;
import com.jogger.beautifulapp.entity.AppCompilationDescData;
import com.jogger.beautifulapp.function.adapter.FindCompilationsAdapter;
import com.jogger.beautifulapp.function.contract.FindCompilationsContract;
import com.jogger.beautifulapp.function.presenter.FindCompilationsPresenter;
import com.jogger.beautifulapp.function.ui.activity.FindCompilationsDescActivity;
import com.jogger.beautifulapp.util.L;
import com.jogger.beautifulapp.util.SizeUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/14.发现合辑
 */

public class FindCompilationsFragment extends BaseFragment<FindCompilationsPresenter> implements
        FindCompilationsContract.View, RefreshRecyclerView.OnRefreshListener, BaseQuickAdapter
        .RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_content)
    RefreshRecyclerView rvContent;
    private FindCompilationsAdapter mAdapter;
    private boolean mIsLoading;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_item;
    }

    @Override
    public void init() {
        rvContent.setLayoutManager(new MyGridLayoutManager(mActivity, 2));
        rvContent.addItemDecoration(new SpaceItemDecoration(SizeUtil.dp2px(5)));
        mAdapter = new FindCompilationsAdapter(null);
        rvContent.setOnRefreshListener(this);
        rvContent.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, rvContent);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void loadData() {
        mPresenter.getFindCompilationsDatas();
    }

    @Override
    protected FindCompilationsPresenter createPresenter() {
        return new FindCompilationsPresenter();
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void getFindCompilationsDatasSuccess(List<Album> albums) {
        mAdapter.setNewData(albums);
        rvContent.onStopRefresh();
    }

    @Override
    public void getMoreDatasSuccess(List<Album> albums) {
        mAdapter.addData(albums);
        mIsLoading = false;
        mAdapter.loadMoreComplete();
    }

    @Override
    public void getMoreDatasFail() {
        mAdapter.loadMoreFail();
        mIsLoading = false;
    }

    @Override
    public void getCompilationDescDatasSuccess(AppCompilationDescData appCompilationDescData) {
        L.e("--------appCompilationDescData:" + appCompilationDescData);
        startNewActivity(FindCompilationsDescActivity.class, Constant.APP_COMPILATION_DESC_DATA, appCompilationDescData);
    }

    @Override
    public void getCompilationDescDatasFail() {
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
        Album album = (Album) adapter.getItem(position);
        if (album == null) return;
        mPresenter.getCompilationDescDatas(album.getId());
    }
}
