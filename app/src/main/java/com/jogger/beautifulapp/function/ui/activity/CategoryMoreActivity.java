package com.jogger.beautifulapp.function.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseActivity;
import com.jogger.beautifulapp.base.recyclerview.DividerLinearItemDecoration;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.base.recyclerview.refresh.RefreshRecyclerView;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.Category;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.entity.TopApp;
import com.jogger.beautifulapp.function.adapter.CategoryAdapter;
import com.jogger.beautifulapp.function.contract.CategoryMoreContract;
import com.jogger.beautifulapp.function.presenter.CategoryMorePresenter;
import com.jogger.beautifulapp.util.L;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//精选分类更多
public class CategoryMoreActivity extends BaseActivity<CategoryMorePresenter> implements CategoryMoreContract.View, RefreshRecyclerView.OnRefreshListener, BaseQuickAdapter
        .RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_return)
    TextView tvReturn;
    @BindView(R.id.rv_content)
    RefreshRecyclerView rvContent;
    private CategoryAdapter.ItemAdapter mAdapter;
    private boolean mIsLoading;
    private Category mCategory;

    @Override
    protected CategoryMorePresenter createPresenter() {
        mCategory = (Category) getIntent().getSerializableExtra(Constant.CATEGORY);
        return new CategoryMorePresenter(mCategory.getId());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_category_more;
    }

    @Override
    protected void init() {
        super.init();
        if (mCategory == null) return;
        tvReturn.setText(mCategory.getTitle());
        rvContent.setLayoutManager(new MyLinearLayoutManager(this));
        rvContent.addItemDecoration(new DividerLinearItemDecoration(this));
        mAdapter = new CategoryAdapter.ItemAdapter(mCategory.getTop_apps());
        rvContent.setOnRefreshListener(this);
        rvContent.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, rvContent);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void loadData() {
        mPresenter.getCategoryDatas();
    }

    @OnClick({R.id.tv_return})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_return:
                finish();
                break;
        }
    }

    @Override
    public void getCategoryDatasSuccess(List<TopApp> topApps) {
        L.e("-------getCategoryDatasSuccess:" + topApps);
        mAdapter.setNewData(topApps);
        rvContent.onStopRefresh();
    }

    @Override
    public void getMoreDatasSuccess(List<TopApp> topApps) {
        mAdapter.addData(topApps);
        mIsLoading = false;
        mAdapter.loadMoreComplete();
    }

    @Override
    public void getMoreDatasFail() {
        mAdapter.loadMoreFail();
        mIsLoading = false;
    }

    @Override
    public void getDesc1DatasSuccess(AppInfo appInfo) {
        startNewActivity(FindChoiceDescActivity.class, Constant.APP_INFO, appInfo);
    }

    @Override
    public void getDesc2DatasSuccess(RecentAppData recentAppData) {
        startNewActivity(RecentDescActivity.class, Constant.APP_DATA, recentAppData);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TopApp topApp = (TopApp) adapter.getItem(position);
        if (topApp == null) return;
        int type = Constant.TYPE_IMG_DESC;//带图详情页
        if (Constant.TYPE_COMMUNITY.equals(topApp.getType())) {
            type = Constant.TYPE_RECENT_DESC;
        }
        mPresenter.getDescDatas(topApp.getId(), type);
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
    public void onRefresh() {
        loadData();
    }
}
