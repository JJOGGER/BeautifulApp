package com.jogger.beautifulapp.function.ui.fragment;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.entity.AppNiceFriendData;
import com.jogger.beautifulapp.function.contract.FindNiceFriendContract;
import com.jogger.beautifulapp.function.presenter.FindNiceFriendPresenter;

/**
 * Created by Jogger on 2018/6/14.fin
 */

public class FindNiceFriendFragment extends BaseFragment<FindNiceFriendPresenter> implements
        FindNiceFriendContract.View {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_item;
    }

    @Override
    public void loadDatas(AppNiceFriendData appData) {

    }

    @Override
    protected FindNiceFriendPresenter createPresenter() {
        return null;
    }
}
