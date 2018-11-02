package com.jogger.beautifulapp.function.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Slide;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.jogger.beautifulapp.BuildConfig;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseActivity;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.Category;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.entity.Tag;
import com.jogger.beautifulapp.entity.TopApp;
import com.jogger.beautifulapp.function.adapter.CategoryAdapter;
import com.jogger.beautifulapp.function.contract.SearchContract;
import com.jogger.beautifulapp.function.presenter.SearchPresenter;
import com.jogger.beautifulapp.util.SizeUtil;
import com.jogger.beautifulapp.util.Util;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View, TextWatcher, CategoryAdapter.OnCategoryAdapterClickListener {
    @BindView(R.id.ibtn_delete)
    ImageButton ibtnDelete;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.fbl_tags)
    FlexboxLayout fblTags;
    private CategoryAdapter mAdapter;
    private InputMethodManager mInputMethodManager;
    private static final int REQUEST_INSTALL_PACKAGES_CODE = 0x0a;
    private static final int GET_UNKNOWN_APP_SOURCES = 0x0b;
    private String mApkName;

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        super.init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Slide().setDuration(200));
            getWindow().setExitTransition(new Slide().setDuration(200));
        }
        mPresenter.getSearchTags();
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert mInputMethodManager != null;
        mInputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        etSearch.addTextChangedListener(this);
        mAdapter = new CategoryAdapter(null);
        mAdapter.setOnCategoryAdapterClickListener(this);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setAdapter(mAdapter);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        ibtnDelete.setVisibility(!TextUtils.isEmpty(charSequence) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @OnClick({R.id.ibtn_delete, R.id.ibtn_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_delete:
                etSearch.setText("");
                break;
            case R.id.ibtn_search:
                search();
                break;
        }
    }

    /**
     * 搜索
     */
    private void search() {
        if (TextUtils.isEmpty(getKeyword())) return;
        closeSoftInput();
        mPresenter.search();
    }

    @Override
    public String getKeyword() {
        return etSearch.getText().toString().trim();
    }

    @Override
    public void getSearchTagsSuccess(List<Tag> tags) {
        rvContent.setVisibility(View.GONE);
        for (int i = 0; i < tags.size(); i++) {
            fblTags.addView(createTextView(tags.get(i)));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeSoftInput();
    }

    @Override
    public void searchSuccess(List<Category> appSearchData) {
        mAdapter.setNewData(appSearchData);
        fblTags.setVisibility(View.GONE);
        rvContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDesc1DatasSuccess(AppInfo appInfo) {
        startNewActivity(FindChoiceDescActivity.class, Constant.APP_INFO, appInfo);
    }

    @Override
    public void getDesc2DatasSuccess(RecentAppData recentAppData) {
        startNewActivity(RecentDescActivity.class, Constant.APP_DATA, recentAppData);
    }

    private View createTextView(final Tag tag) {
        FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        params.leftMargin = SizeUtil.dp2px(1);
        params.topMargin = SizeUtil.dp2px(8);
        params.bottomMargin = SizeUtil.dp2px(1);
        params.rightMargin = SizeUtil.dp2px(8);
        textView.setBackgroundColor(getResources().getColor(R.color.search_tag_selector));
        textView.setPadding(SizeUtil.dp2px(16), SizeUtil.dp2px(8), SizeUtil.dp2px(16), SizeUtil.dp2px(8));
        textView.setText(tag.getTitle());
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, SizeUtil.dp2px(16));
        textView.setTextColor(Color.WHITE);
        textView.setLayoutParams(params);
        textView.setTag(tag.getTitle());
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText(textView.getTag().toString());
                search();
            }
        });
        return textView;
    }

    private void closeSoftInput() {
        mInputMethodManager.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
    }

    @Override
    public void onItemClick(TopApp app) {
        if (Constant.TYPE_COMMUNITY.equals(app.getType())) {
            mPresenter.getDescDatas(app.getId(), Constant.TYPE_RECENT_DESC);
        } else {
            mPresenter.getDescDatas(app.getId(), Constant.TYPE_IMG_DESC);
        }
    }

    @Override
    public void onDownloadCompleted(String name) {
        mApkName = name;
        checkInstallByO();
    }

    @Override
    public void onMore(Category category) {
        startNewActivity(SearchMoreActivity.class, Constant.CATEGORY, category);
    }

    /**
     * android8.0安装权限
     */
    private void checkInstallByO() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean b = Util.getApp().getPackageManager().canRequestPackageInstalls();
            if (b) {
                installApk();//安装应用的逻辑(写自己的就可以)  
            } else {
                //请求安装未知应用来源的权限  
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.REQUEST_INSTALL_PACKAGES
                }, REQUEST_INSTALL_PACKAGES_CODE);
            }
        } else {
            installApk();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_INSTALL_PACKAGES_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    installApk();
                } else {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                    startActivityForResult(intent, GET_UNKNOWN_APP_SOURCES);
                }
                break;
            case GET_UNKNOWN_APP_SOURCES:
                checkInstallByO();
                break;

        }
    }

    private void installApk() {
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + mApkName + ".apk";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = new File(filePath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }
}
