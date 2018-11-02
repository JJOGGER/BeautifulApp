package com.jogger.beautifulapp.function.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.base.recyclerview.UnScrollLinearLayoutManager;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppCompilationDescData;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.CompilationDesc;
import com.jogger.beautifulapp.entity.DownloadUrl;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.function.adapter.FindCompilationDescAdapter;
import com.jogger.beautifulapp.function.contract.FindCompilationsDescContract;
import com.jogger.beautifulapp.function.presenter.FindCompilationsDescPresenter;
import com.jogger.beautifulapp.function.ui.dialog.DownloadDialog;
import com.jogger.beautifulapp.swipelayoutlib.app.SwipeBackActivity;
import com.jogger.beautifulapp.widget.DescImageView;
import com.jogger.beautifulapp.widget.DescScrollView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//合辑详情
public class FindCompilationsDescActivity extends SwipeBackActivity<FindCompilationsDescPresenter> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, FindCompilationsDescContract.View {
    @BindView(R.id.nsv_content)
    NestedScrollView nsvContent;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.iv_title)
    DescImageView ivTitle;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    private DownloadDialog mDownloadDialog;

    @Override
    protected IPresenter createPresenter() {
        return new FindCompilationsDescPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_find_compilations_desc;
    }

    @Override
    protected void init() {
        super.init();
        AppCompilationDescData compilationDescData = (AppCompilationDescData) getIntent().getSerializableExtra(Constant.APP_COMPILATION_DESC_DATA);
        if (compilationDescData == null || compilationDescData.getApps() == null) return;
        UnScrollLinearLayoutManager layoutManager = new UnScrollLinearLayoutManager(this);
        FindCompilationDescAdapter adapter = new FindCompilationDescAdapter(compilationDescData.getApps());
        rvContent.setLayoutManager(layoutManager);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);
        rvContent.setAdapter(adapter);
        rvContent.setFocusable(false);
        Glide.with(this).load(compilationDescData.getCover_image())
                .into(ivTitle);
        tvHeaderTitle.setText(compilationDescData.getTitle());
        tvHeader.setText(compilationDescData.getDigest());
        ivTitle.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivTitle.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ((DescScrollView) nsvContent).setEnlarge(ivTitle);
            }
        });
    }

    @OnClick({R.id.ibtn_return})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_return:
                finish();
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        CompilationDesc compilationDesc = (CompilationDesc) adapter.getItem(position);
        if (compilationDesc == null) return;
        if (view.getId() == R.id.tv_download) {
            if (mDownloadDialog == null)
                mDownloadDialog = new DownloadDialog();
            Bundle bundle = new Bundle();
            bundle.putLong(Constant.DOWNLOAD_ID, compilationDesc.getId());
            if (!"0.0M".equals(compilationDesc.getSize()))
                bundle.putString(Constant.SIZE, compilationDesc.getSize());
            List<DownloadUrl> downloadUrls = new ArrayList<>();
            if (compilationDesc.getLinks() != null && !compilationDesc.getLinks().isEmpty())
                for (int i = 0; i < compilationDesc.getLinks().size(); i++) {
                    CompilationDesc.Link link = compilationDesc.getLinks().get(i);
                    DownloadUrl downloadUrl = new DownloadUrl();
                    if (TextUtils.isEmpty(link.getUrl()))
                        continue;
                    downloadUrl.setUrl(link.getUrl());
                    downloadUrl.setId(compilationDesc.getId());
                    downloadUrl.setName(compilationDesc.getTitle());
                    downloadUrl.setChannel(link.getType());
                    downloadUrls.add(downloadUrl);
                }
            bundle.putSerializable(Constant.DOWNLOAD_URLS, (Serializable) downloadUrls);
            mDownloadDialog.setArguments(bundle);
            mDownloadDialog.show(getSupportFragmentManager(), DownloadDialog.class.getSimpleName());
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CompilationDesc compilationDesc = (CompilationDesc) adapter.getItem(position);
        if (compilationDesc == null || compilationDesc.getId() == 0) return;
        mPresenter.getDescDatas(compilationDesc.getArticle_id(), compilationDesc.getArticle_type());
    }


    @Override
    public void getDesc1DatasSuccess(AppInfo appInfo) {
        startNewActivity(FindChoiceDescActivity.class, Constant.APP_INFO, appInfo);
    }

    @Override
    public void getDesc2DatasSuccess(RecentAppData recentAppData) {
        startNewActivity(RecentDescActivity.class, Constant.APP_DATA, recentAppData);
    }
}
