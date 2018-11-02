package com.jogger.beautifulapp.function.ui.activity;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseActivity;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.base.recyclerview.refresh.RefreshRecyclerView;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.function.adapter.FindRecentAdapter;
import com.jogger.beautifulapp.function.contract.TagsMoreContract;
import com.jogger.beautifulapp.function.presenter.TagsMorePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jogger on 2018/11/2.标签详情页
 */
public class TagsMoreActivity extends BaseActivity<TagsMorePresenter> implements
        TagsMoreContract.View, RefreshRecyclerView.OnRefreshListener, BaseQuickAdapter
        .RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_content)
    RefreshRecyclerView rvContent;
    private FindRecentAdapter mAdapter;
    private boolean mIsLoading;

    @Override
    protected TagsMorePresenter createPresenter() {
        int id = getIntent().getIntExtra(Constant.ID, -1);
        return new TagsMorePresenter(id);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tags_more;
    }

    @Override
    public void init() {
        rvContent.setLayoutManager(new MyLinearLayoutManager(this));
        mAdapter = new FindRecentAdapter(null);
        rvContent.setAdapter(mAdapter);
        rvContent.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnLoadMoreListener(this, rvContent);
    }

    @Override
    public void loadData() {
        mPresenter.getTagsDatas(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.getTagsDatas(true);
    }

    @Override
    public void getTagsDataSuccess(List<RecentAppData> recentAppDatas) {
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

    @OnClick({R.id.iv_icon})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_icon:
                finish();
                break;
        }
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
