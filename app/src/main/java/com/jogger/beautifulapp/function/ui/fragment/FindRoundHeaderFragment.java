package com.jogger.beautifulapp.function.ui.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.MediaArticle;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/14.
 * 周边头部
 */

public class FindRoundHeaderFragment extends BaseFragment {
    @BindView(R.id.iv_content)
    ImageView ivContent;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_round_header;
    }

    @Override
    protected IPresenter createPresenter() {
        return null;
    }

    @Override
    public void init() {
        Bundle arguments = getArguments();
        if (arguments == null) return;
        MediaArticle mediaArticle = (MediaArticle) getArguments().getSerializable(Constant
                .MEDIA_ARTICLE);
        if (mediaArticle == null) return;
        Glide.with(this)
                .load(mediaArticle.getCover_image())
                .into(ivContent);
        tvTitle.setText(mediaArticle.getTitle());
        tvSubTitle.setText(mediaArticle.getSub_title());
    }
}
