package com.jogger.beautifulapp.function.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.entity.AppNiceFriendData;
import com.jogger.beautifulapp.function.adapter.FindNiceFriendAdapter;
import com.jogger.beautifulapp.function.contract.FindNiceFriendContract;
import com.jogger.beautifulapp.function.presenter.FindNiceFriendPresenter;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/14.发现美友
 */

public class FindNiceFriendFragment extends BaseFragment<FindNiceFriendPresenter> implements
        FindNiceFriendContract.View {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
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
        mPresenter.getFindNickFriendDatas();
    }

    @Override
    public void loadDatas(AppNiceFriendData appData) {
        mAdapter.setNewData(appData.getUsers_rank());
    }

    @Override
    protected FindNiceFriendPresenter createPresenter() {
        return new FindNiceFriendPresenter();
    }
}
