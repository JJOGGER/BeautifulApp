package com.jogger.beautifulapp.function.ui.fragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.base.recyclerview.refresh.RefreshRecyclerView;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.MediaArticle;
import com.jogger.beautifulapp.function.adapter.FindRoundAdapter;
import com.jogger.beautifulapp.function.contract.FindRoundContract;
import com.jogger.beautifulapp.function.presenter.FindRoundPresenter;
import com.jogger.beautifulapp.function.ui.activity.FindChoiceDescActivity;
import com.jogger.beautifulapp.util.L;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/13.发现周边
 */

public class FindRoundFragment extends BaseFragment<FindRoundPresenter> implements
        FindRoundContract.View, RefreshRecyclerView.OnRefreshListener, BaseQuickAdapter
        .RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_content)
    RefreshRecyclerView rvContent;
    private FindRoundAdapter mAdapter;
    private boolean mIsLoading;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_item;
    }

    @Override
    protected FindRoundPresenter createPresenter() {
        return new FindRoundPresenter();
    }

    @Override
    public void init() {
        rvContent.setLayoutManager(new MyLinearLayoutManager(mActivity));
        mAdapter = new FindRoundAdapter(this, null);
        rvContent.setAdapter(mAdapter);
        rvContent.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, rvContent);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void loadData() {
        mPresenter.getFindRoundTopDatas();
        mPresenter.getFindRoundDatas();
    }

    @Override
    public void onRefresh() {
        loadData();
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
    public void getFindRoundTopDatasSuccess(List<MediaArticle> mediaArticles) {
        mAdapter.setHeaderDatas(mediaArticles);
    }

    @Override
    public void getFindRoundDatasSuccess(List<MediaArticle> mediaArticles) {
        mAdapter.setNewData(mediaArticles);
        rvContent.onStopRefresh();
        mAdapter.loadMoreComplete();
    }

    @Override
    public void getMoreDatasSuccess(List<MediaArticle> mediaArticles) {
        mAdapter.addData(mediaArticles);
        mAdapter.loadMoreComplete();
        mIsLoading = false;
    }

    @Override
    public void getMoreDatasFail() {
        mAdapter.loadMoreFail();
        mIsLoading = false;
    }

    @Override
    public void getDescDatasSuccess(AppInfo info) {
        startNewActivity(FindChoiceDescActivity.class, Constant.APP_INFO, info);
    }

    @Override
    public void getDescDatasFail() {
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        L.e("------------::" + adapter.getItem(position));
        MediaArticle mediaArticle = (MediaArticle) adapter.getItem(position);
        if (mediaArticle == null) return;
        mPresenter.getDescDatas(mediaArticle.getId());
    }
}
