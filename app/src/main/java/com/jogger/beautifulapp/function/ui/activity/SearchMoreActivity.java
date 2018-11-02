package com.jogger.beautifulapp.function.ui.activity;

import android.view.View;

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
import com.jogger.beautifulapp.function.adapter.SearchItemAdapter;
import com.jogger.beautifulapp.function.contract.FindCompilationsDescContract;
import com.jogger.beautifulapp.function.presenter.FindCompilationsDescPresenter;
import com.jogger.beautifulapp.util.L;

import butterknife.BindView;
import butterknife.OnClick;

//精选分类更多
public class SearchMoreActivity extends BaseActivity<FindCompilationsDescPresenter> implements FindCompilationsDescContract.View,  BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_content)
    RefreshRecyclerView rvContent;

    @Override
    protected FindCompilationsDescPresenter createPresenter() {
        return new FindCompilationsDescPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_category_more;
    }

    @Override
    protected void init() {
        super.init();
        Category category = (Category) getIntent().getSerializableExtra(Constant.CATEGORY);
        if (category == null) return;
        rvContent.setLayoutManager(new MyLinearLayoutManager(this));
        rvContent.addItemDecoration(new DividerLinearItemDecoration(this));
        SearchItemAdapter adapter = new SearchItemAdapter(category.getTop_apps());
        rvContent.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
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
    public void getDesc1DatasSuccess(AppInfo appInfo) {
        startNewActivity(FindChoiceDescActivity.class, Constant.APP_INFO, appInfo);
    }

    @Override
    public void getDesc2DatasSuccess(RecentAppData recentAppData) {
        startNewActivity(RecentDescActivity.class, Constant.APP_DATA, recentAppData);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        L.e("----------" + adapter.getItem(position));
        TopApp topApp = (TopApp) adapter.getItem(position);
        if (topApp == null) return;
        int type = Constant.TYPE_IMG_DESC;//带图详情页
        if (Constant.TYPE_COMMUNITY.equals(topApp.getType())) {
            type = Constant.TYPE_RECENT_DESC;
        }
        mPresenter.getDescDatas(topApp.getId(), type);
    }
}
