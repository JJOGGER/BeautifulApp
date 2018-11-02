package com.jogger.beautifulapp.function.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.jogger.beautifulapp.BuildConfig;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.refresh.RefreshRecyclerView;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppCategoryData;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.Category;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.entity.TopApp;
import com.jogger.beautifulapp.function.adapter.CategoryAdapter;
import com.jogger.beautifulapp.function.contract.CategoryContract;
import com.jogger.beautifulapp.function.presenter.CategoryPresenter;
import com.jogger.beautifulapp.function.ui.activity.CategoryMoreActivity;
import com.jogger.beautifulapp.function.ui.activity.FindChoiceDescActivity;
import com.jogger.beautifulapp.function.ui.activity.MainActivity;
import com.jogger.beautifulapp.function.ui.activity.RecentDescActivity;
import com.jogger.beautifulapp.util.SPUtil;

import java.io.File;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/10.精选分类
 */

public class CategoryFragment extends BaseFragment<CategoryPresenter> implements CategoryContract
        .View, CategoryAdapter.OnCategoryAdapterClickListener, RefreshRecyclerView.OnRefreshListener {
    @BindView(R.id.cl_container)
    ConstraintLayout clContainer;
    @BindView(R.id.rv_content)
    RefreshRecyclerView rvContent;
    private CategoryAdapter mAdapter;
    private String mApkName;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected CategoryPresenter createPresenter() {
        return new CategoryPresenter();
    }

    @Override
    public void init() {
        mAdapter = new CategoryAdapter(null);
        rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        rvContent.setAdapter(mAdapter);
        rvContent.setOnRefreshListener(this);
        mAdapter.setOnCategoryAdapterClickListener(this);
    }

    @Override
    public void loadData() {
        super.loadData();
        mPresenter.getCategoryDatas();
    }

    @Override
    public void onResume() {
        super.onResume();
        setBgColor();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mIsViewCreated) {
            setBgColor();
        }
    }

    private void setBgColor() {
        int color = SPUtil.getInstance().getInt(Constant.CATEGORY_LAST_COLOR, getResources().getColor(R.color.colorAccent));
        clContainer.setBackgroundColor(color);
        ((MainActivity) mActivity).getBaseContainer().setBackgroundColor(color);
    }


    @Override
    public void getCategoryDatasSuccess(AppCategoryData appCategoryData) {
        mAdapter.setNewData(appCategoryData.getApps());
        rvContent.onStopRefresh();
    }

    @Override
    public void onItemClick(TopApp app) {
        int type = Constant.TYPE_IMG_DESC;//带图详情页
        if (Constant.TYPE_COMMUNITY.equals(app.getType())) {
            type = Constant.TYPE_RECENT_DESC;
        }
        mPresenter.getDescDatas(app.getId(), type);
    }

    @Override
    public void onDownloadCompleted(String name) {
        mApkName = name;
        installApk();
    }

    @Override
    public void onMore(Category category) {
        startNewActivity(CategoryMoreActivity.class, Constant.CATEGORY, category);
    }

    private void installApk() {
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + mApkName + ".apk";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = new File(filePath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mActivity, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        loadData();
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
