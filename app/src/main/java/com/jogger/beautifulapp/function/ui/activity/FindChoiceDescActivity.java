package com.jogger.beautifulapp.function.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseDescActivity;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.DownloadMethod;
import com.jogger.beautifulapp.entity.DownloadUrl;
import com.jogger.beautifulapp.function.presenter.ChoiceDescPresenter;
import com.jogger.beautifulapp.function.ui.dialog.DownloadDialog;
import com.jogger.beautifulapp.util.DescTextUtil;
import com.jogger.beautifulapp.widget.DescImageView;
import com.jogger.beautifulapp.widget.DescScrollView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FindChoiceDescActivity extends BaseDescActivity implements DescScrollView.OnScrollListener {
    @BindView(R.id.fl_top_container)
    FrameLayout flTopContainer;
    @BindView(R.id.iv_title)
    DescImageView ivTitle;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;
    @BindView(R.id.ibtn_share)
    ImageButton ibtnShare;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.rl_roll_menu)
    RelativeLayout rlRollMenu;
    @BindView(R.id.rl_menu)
    RelativeLayout rlMenu;
    @BindView(R.id.vp_bottom)
    ViewPager vpBottom;
    private DownloadDialog mDownloadDialog;
    private AppInfo mAppInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_find_choice_desc;
    }

    @Override
    protected ChoiceDescPresenter createPresenter() {
        return new ChoiceDescPresenter();
    }


    @Override
    protected void init() {
        super.init();
        ((DescScrollView) nsvContent).setOnScrollListener(this);
        mAppInfo = (AppInfo) getIntent().getSerializableExtra(Constant.APP_INFO);
        if (mAppInfo == null) return;
        Glide.with(this)
                .load(mAppInfo.getCover_image())
                .into(ivTitle);
        Glide.with(this)
                .load(mAppInfo.getIcon_image())
                .into(ivIcon);
        tvTitle.setText(mAppInfo.getTitle());
        tvSubTitle.setText(mAppInfo.getSub_title());
        if (mAppInfo.getOther_download_url() == null ||
                mAppInfo.getOther_download_url().isEmpty() ||
                TextUtils.isEmpty(mAppInfo.getDownload_url())) {
            setDownloadEnable(false);
            mBottomPagerAdapter.setDownloadEnable(false);
        }
        tvDesc.setMovementMethod(LinkMovementMethod.getInstance());
        new DescTextUtil().setDrawableText(mAppInfo.getContent(), tvDesc);
        ivTitle.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivTitle.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ((DescScrollView) nsvContent).setEnlarge(ivTitle);
            }
        });

    }

    @Override
    public void onScrollY(int scrollY) {
        if (scrollY >= mShowY) {
            rlRollMenu.setVisibility(View.INVISIBLE);
            rlMenu.setVisibility(View.VISIBLE);
        } else if (scrollY < mShowY) {
            if (rlMenu.getVisibility() == View.VISIBLE) {
                rlRollMenu.setVisibility(View.VISIBLE);
                rlMenu.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void scollUp(int scrollUpY) {

    }


    @Override
    public void onDownload() {
        download();
    }

    @Override
    public void download() {
        if (mDownloadDialog == null)
            mDownloadDialog = new DownloadDialog();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.SIZE, mAppInfo.getSize());
        List<DownloadUrl> downloadUrls = new ArrayList<>();
        if (!TextUtils.isEmpty(mAppInfo.getDownload_url())) {
            DownloadUrl google = new DownloadUrl();
            google.setChannel(Constant.CHANNEL_GOOGLE);
            google.setUrl(mAppInfo.getDownload_url());
            google.setName(mAppInfo.getTitle());
            google.setId(mAppInfo.getId());
            downloadUrls.add(google);
        }
        if (mAppInfo.getOther_download_url() != null && !mAppInfo.getOther_download_url().isEmpty())
            for (int i = 0; i < mAppInfo.getOther_download_url().size(); i++) {
                DownloadMethod downloadMethod = mAppInfo.getOther_download_url().get(i);
                DownloadUrl downloadUrl = new DownloadUrl();
                downloadUrl.setUrl(downloadMethod.getDownload_url());
                downloadUrl.setName(mAppInfo.getTitle());
                downloadUrl.setChannel(downloadMethod.getApp_market_name());
                downloadUrl.setId(mAppInfo.getId());
                downloadUrls.add(downloadUrl);
            }
        bundle.putSerializable(Constant.DOWNLOAD_URLS, (Serializable) downloadUrls);
        mDownloadDialog.setArguments(bundle);
        mDownloadDialog.show(getSupportFragmentManager(), DownloadDialog.class.getSimpleName());
    }
}
