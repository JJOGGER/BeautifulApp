package com.jogger.beautifulapp.function.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.entity.User;
import com.jogger.beautifulapp.function.adapter.UserCommandAdapter;
import com.jogger.beautifulapp.function.contract.UserHomeRecomendContract;
import com.jogger.beautifulapp.function.presenter.UserHomeRecomendPresenter;
import com.jogger.beautifulapp.function.ui.activity.UserHomeActivity;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/17.
 * 用户推荐
 */

public class UserHomeRecommendFragment extends BaseFragment<UserHomeRecomendPresenter> implements
        UserHomeRecomendContract.View {
//    @BindView(R.id.tv_flowers)
//    TextView tvFlowers;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private UserCommandAdapter mAdapter;
    private User mUser;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_home_recommend;
    }

    @Override
    public void init() {
        if (getArguments() == null) return;
        mUser = (User) getArguments().getSerializable(Constant.USER_INFO);
        if (mUser == null) return;
        int flowers = getArguments().getInt(Constant.FLOWERS);
        rvContent.setLayoutManager(new MyLinearLayoutManager(mActivity));
        mAdapter = new UserCommandAdapter(null,flowers);
        rvContent.setAdapter(mAdapter);
        mPresenter.getUserRecommendDatas(1, 20);
        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //向上dy负值
                if (mActivity instanceof UserHomeActivity) {
                    ((UserHomeActivity) mActivity).scrollToTop(!rvContent.canScrollVertically(-1)
                            , dy);
                }
            }
        });
    }

    @Override
    public void getUserRecommendDatasSuccess(AppRecentData appRecentData) {
        mAdapter.setNewData(appRecentData.getApps());
    }

    @Override
    protected UserHomeRecomendPresenter createPresenter() {
        Bundle arguments = getArguments();
        if (arguments == null) return null;
        mUser = (User) arguments.getSerializable(Constant.USER_INFO);
        if (mUser == null) return null;
        return new UserHomeRecomendPresenter(mUser.getId());
    }
}
