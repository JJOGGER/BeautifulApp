package com.jogger.beautifulapp.function.ui.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.function.contract.DialyPagerContract;
import com.jogger.beautifulapp.function.presenter.DialyPagerPresenter;
import com.jogger.beautifulapp.function.ui.activity.FindChoiceDescActivity;
import com.jogger.beautifulapp.util.NetworkUtil;
import com.jogger.beautifulapp.util.T;

import butterknife.BindView;
import butterknife.OnClick;


public class DialiyPageFragment extends BaseFragment<DialyPagerPresenter> implements DialyPagerContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;
    @BindView(R.id.iv_content)
    ImageView ivContent;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_flowers)
    TextView tvFlowers;
    private AppInfo mAppInfo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dialy_pager;
    }

    @Override
    public void init() {
        Bundle arguments = getArguments();
        if (arguments == null) return;
        mAppInfo = (AppInfo) arguments.getSerializable(Constant.APP_INFO);
        if (mAppInfo == null) return;
        tvTitle.setText(mAppInfo.getTitle());
        tvSubTitle.setText(mAppInfo.getSub_title());
        tvDesc.setText(mAppInfo.getDigest());
        Glide.with(mActivity)
                .load(mAppInfo.getCover_image())
                .into(ivContent);
        tvFlowers.setText(String.valueOf(mAppInfo.getInfo().getUp()));
    }

    @Override
    protected DialyPagerPresenter createPresenter() {
        return new DialyPagerPresenter();
    }

    @OnClick(R.id.cl_main)
    public void onClick() {
        if (mAppInfo == null) return;
        if (!NetworkUtil.isNetworkAvailable()) {
            T.show(R.string.request_failure);
            return;
        }
        mPresenter.getChoiceDescData(mAppInfo.getId());
    }

    @Override
    public void getChoiceDescDataSuccess(AppInfo appInfo) {
        startNewActivity(FindChoiceDescActivity.class, Constant.APP_INFO, appInfo);
    }
}
