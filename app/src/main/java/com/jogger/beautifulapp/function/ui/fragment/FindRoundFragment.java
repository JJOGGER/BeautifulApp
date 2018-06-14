package com.jogger.beautifulapp.function.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.entity.AppMediaArticleData;
import com.jogger.beautifulapp.function.adapter.FindRoundAdapter;
import com.jogger.beautifulapp.function.contract.FindRoundContract;
import com.jogger.beautifulapp.function.presenter.FindRoundPresenter;
import com.jogger.beautifulapp.util.L;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/13.发现周边
 */

public class FindRoundFragment extends BaseFragment<FindRoundPresenter> implements
        FindRoundContract.View {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private FindRoundAdapter mAdapter;

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
        mPresenter.getFindRoundTopDatas();
        mPresenter.getFindRoundDatas(1, 20);
    }

    @Override
    public void loadTopDatas(AppMediaArticleData appMediaArticleData) {
        L.e("------appMediaArticleData-" + appMediaArticleData);
        mAdapter.setHeaderDatas(appMediaArticleData.getMedia_articles());
    }

    @Override
    public void loadDatas(AppMediaArticleData appMediaArticleData) {
        mAdapter.setNewData(appMediaArticleData.getMedia_articles());
    }
}
