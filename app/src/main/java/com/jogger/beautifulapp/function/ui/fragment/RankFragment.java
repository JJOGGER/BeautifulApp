package com.jogger.beautifulapp.function.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.entity.AppSocialArticleData;
import com.jogger.beautifulapp.function.adapter.RankAdapter;
import com.jogger.beautifulapp.function.contract.RankContract;
import com.jogger.beautifulapp.function.presenter.RankPresenter;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/10.应用排行
 */

public class RankFragment extends BaseFragment<RankPresenter> implements RankContract.View {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private RankAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rank;
    }

    @Override
    protected RankPresenter createPresenter() {
        return new RankPresenter();
    }

    @Override
    public void init() {
        mAdapter = new RankAdapter(null);
        rvContent.setLayoutManager(new MyLinearLayoutManager(mActivity));
        rvContent.setAdapter(mAdapter);
        mPresenter.getRankDatas(1, 50);
    }

    @Override
    public void getRankDatasSuccess(AppSocialArticleData appSocialArticleData) {
        mAdapter.setNewData(appSocialArticleData.getApps());
    }
}
