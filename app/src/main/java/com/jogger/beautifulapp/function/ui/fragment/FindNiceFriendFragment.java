package com.jogger.beautifulapp.function.ui.fragment;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.base.recyclerview.refresh.RefreshRecyclerView;
import com.jogger.beautifulapp.entity.UsersRank;
import com.jogger.beautifulapp.function.adapter.FindNiceFriendAdapter;
import com.jogger.beautifulapp.function.contract.FindNiceFriendContract;
import com.jogger.beautifulapp.function.presenter.FindNiceFriendPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/14.发现美友
 */

public class FindNiceFriendFragment extends BaseFragment<FindNiceFriendPresenter> implements
        FindNiceFriendContract.View,RefreshRecyclerView.OnRefreshListener {
    @BindView(R.id.rv_content)
    RefreshRecyclerView rvContent;
    private FindNiceFriendAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_item;
    }

    @Override
    public void init() {
        rvContent.setLayoutManager(new MyLinearLayoutManager(mActivity));
        mAdapter = new FindNiceFriendAdapter(null);
        rvContent.setAdapter(mAdapter);
        rvContent.setOnRefreshListener(this);
    }

    @Override
    public void loadData() {
        mPresenter.getFindNickFriendDatas();
    }

    @Override
    public void getFindNiceFriendDatasSuccess(List<UsersRank> usersRanks) {
        mAdapter.setNewData(usersRanks);
        rvContent.onStopRefresh();
    }

    @Override
    protected FindNiceFriendPresenter createPresenter() {
        return new FindNiceFriendPresenter();
    }

    @Override
    public void onRefresh() {
        loadData();
    }

}
