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
import com.jogger.beautifulapp.function.adapter.FindChoiceAdapter;
import com.jogger.beautifulapp.function.contract.FindChoiceContract;
import com.jogger.beautifulapp.function.presenter.FindChoicePresenter;
import com.jogger.beautifulapp.function.ui.activity.FindChoiceDescActivity;
import com.jogger.beautifulapp.util.NetworkUtil;
import com.jogger.beautifulapp.util.T;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/12.
 * 发现精选页
 */

public class FindChoiceFragment extends BaseFragment<FindChoicePresenter> implements
        FindChoiceContract.View, BaseQuickAdapter.OnItemClickListener,
        RefreshRecyclerView.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv_content)
    RefreshRecyclerView rvContent;
    private FindChoiceAdapter mAdapter;
    private boolean mIsLoading;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_item;
    }

    @Override
    public void init() {
        rvContent.setLayoutManager(new MyLinearLayoutManager(mActivity));
        mAdapter = new FindChoiceAdapter(null);
        rvContent.setAdapter(mAdapter);
        rvContent.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, rvContent);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected FindChoicePresenter createPresenter() {
        return new FindChoicePresenter();
    }

    @Override
    public void loadData() {
        mPresenter.getFindChoiceDatas();
    }

    @Override
    public void getFindChoiceDatasSuccess(List<MediaArticle> mediaArticles) {
        mAdapter.setNewData(mediaArticles);
        rvContent.onStopRefresh();
    }

    @Override
    public void getMoreDatasSuccess(List<MediaArticle> mediaArticles) {
        mAdapter.addData(mediaArticles);
        mAdapter.loadMoreComplete();
        mIsLoading = false;
    }

    @Override
    public void getMoreDatasFail() {
        mIsLoading = false;
        mAdapter.loadMoreFail();
    }

    @Override
    public void getChoiceDescDataSuccess(AppInfo appInfo) {
        startNewActivity(FindChoiceDescActivity.class, Constant.APP_INFO, appInfo);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MediaArticle article = (MediaArticle) adapter.getItem(position);
        if (article == null) return;
        if (!NetworkUtil.isNetworkAvailable()) {
            T.show(R.string.request_failure);
            return;
        }
        mPresenter.getChoiceDescData(article.getId());
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
                mPresenter.getMoreDatas();
            }
        }, 100);
    }
}
