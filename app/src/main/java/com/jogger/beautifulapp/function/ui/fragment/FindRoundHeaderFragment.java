package com.jogger.beautifulapp.function.ui.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.MediaArticle;
import com.jogger.beautifulapp.function.contract.FindRoundHeaderContract;
import com.jogger.beautifulapp.function.presenter.FindRoundHeaderPresenter;
import com.jogger.beautifulapp.function.ui.activity.FindChoiceDescActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jogger on 2018/6/14.
 * 周边头部
 */

public class FindRoundHeaderFragment extends BaseFragment<FindRoundHeaderPresenter> implements FindRoundHeaderContract.View {
    @BindView(R.id.iv_content)
    ImageView ivContent;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;
    private MediaArticle mMediaArticle;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_round_header;
    }

    @Override
    protected FindRoundHeaderPresenter createPresenter() {
        return new FindRoundHeaderPresenter();
    }

    @Override
    public void init() {
        Bundle arguments = getArguments();
        if (arguments == null) return;
        mMediaArticle = (MediaArticle) getArguments().getSerializable(Constant
                .MEDIA_ARTICLE);
        if (mMediaArticle == null) return;
        Glide.with(this)
                .load(mMediaArticle.getCover_image())
                .into(ivContent);
        tvTitle.setText(mMediaArticle.getTitle());
        tvSubTitle.setText(mMediaArticle.getSub_title());
    }

    @OnClick(R.id.cv_container)
    public void onClick() {
        mPresenter.getHeaderDescDatas(mMediaArticle.getId());
    }

    @Override
    public void getHeaderDescDatasSuccess(AppInfo appInfo) {
        startNewActivity(FindChoiceDescActivity.class, Constant.APP_INFO, appInfo);
    }

    @Override
    public void getHeaderDescDatasFail() {
    }
}
