package com.jogger.beautifulapp.function.ui.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.function.contract.DialyPagerContract;

import butterknife.BindView;


public class DialiyPageFragment extends BaseFragment implements DialyPagerContract.View {

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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dialy_pager;
    }

    @Override
    public void init() {
        Bundle arguments = getArguments();
        if (arguments == null) return;
        AppInfo appInfo = (AppInfo) arguments.getSerializable(Constant.APP_INFO);
        if (appInfo == null) return;
        tvTitle.setText(appInfo.getTitle());
        tvSubTitle.setText(appInfo.getSub_title());
        tvDesc.setText(appInfo.getDigest());
        Glide.with(mActivity)
                .load(appInfo.getCover_image())
                .into(ivContent);
        tvFlowers.setText(String.valueOf(appInfo.getPrice()));
    }

    @Override
    protected IPresenter createPresenter() {
        return null;
    }

}
