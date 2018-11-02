package com.jogger.beautifulapp.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.control.ActivityCollector;
import com.jogger.beautifulapp.util.StatusUtil;
import com.jogger.beautifulapp.widget.LoadingWindow;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jogger on 2018/1/10.
 * 基类activity
 */
@SuppressWarnings("unchecked")
public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity implements
        BaseView {
    private static final int STATUS_COLOR = Color.parseColor("#3f000000");
    private ProgressDialog mProgressDialog;
    protected T mPresenter;
    private Unbinder mBind;
    private LoadingWindow mLoadingWindow;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getWindow().setBackgroundDrawable(null);
        mBind = ButterKnife.bind(this);
        ActivityCollector.addActivity(this);
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView(this);
        //开启沉浸式状态栏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (isFullScreen()) {
            StatusUtil.setActivityFullScreen(this);
        } else {
            StatusUtil.immersive(this, immersiveColor());
        }
        init();
        loadData();
    }

    protected abstract T createPresenter();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void init() {
    }

    protected void loadData() {
    }

    /**
     * 是否开启沉浸式状态栏
     */
    public boolean isImmersive() {
        return true;
    }

    /**
     * 状态栏颜色
     */
    public int immersiveColor() {
        return STATUS_COLOR;
    }


    /**
     * 是否全屏
     */
    public boolean isFullScreen() {
        return false;
    }

    public void showProgressDialog() {
        if (isFinishing() || getSupportFragmentManager() == null)
            return;
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.waiting));
        }
        if (!mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    public void showProgressDialog(String message) {
        if (isFinishing() || getSupportFragmentManager() == null)
            return;
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.setMessage(message);
        if (!mProgressDialog.isShowing())
            mProgressDialog.show();
    }
    @Override
    public void showLoadingWindow() {
        if (isFinishing() || isDestroyed()) return;
        if (mLoadingWindow == null)
            mLoadingWindow = new LoadingWindow();
        mLoadingWindow.show(this);
    }

    @Override
    public void dismissLoadingWindow() {
        if (isFinishing() ||isDestroyed()) return;
        if (mLoadingWindow != null) {
            mLoadingWindow.dismiss();
            mLoadingWindow = null;
        }
    }
    public void cancelProgressDialog() {
        if (mProgressDialog == null) return;
        if (mProgressDialog.isShowing())
            mProgressDialog.cancel();
        mProgressDialog = null;
    }

    public void startNewActivity(Class<? extends AppCompatActivity> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void startNewActivityForResult(Class<? extends AppCompatActivity> cls, int result) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, result);
    }


    /**
     * 弹出权限提示
     */

    public void startNewActivity(Class cls, String key, Object value) {
        Intent intent = new Intent(this, cls);
        if (value instanceof Integer) {
            intent.putExtra(key, (Integer) value);
        }
        if (value instanceof Integer[]) {
            intent.putExtra(key, (Integer[]) value);
        }
        if (value instanceof String) {
            intent.putExtra(key, (String) value);
        }
        if (value instanceof String[]) {
            intent.putExtra(key, (String[]) value);
        }
        if (value instanceof Boolean) {
            intent.putExtra(key, (Boolean) value);
        }
        if (value instanceof Byte) {
            intent.putExtra(key, (Byte) value);
        }
        if (value instanceof Byte[]) {
            intent.putExtra(key, (Byte[]) value);
        }
        if (value instanceof Serializable) {
            intent.putExtra(key, (Serializable) value);
        }

        if (value instanceof Serializable[]) {
            intent.putExtra(key, (Serializable[]) value);
        }
        if (value instanceof Parcelable) {
            intent.putExtra(key, (Parcelable) value);
        }
        if (value instanceof Parcelable[]) {
            intent.putExtra(key, (Parcelable[]) value);
        }
        if (value instanceof Float[]) {
            intent.putExtra(key, (Float[]) value);
        }
        startActivity(intent);
    }


    public void startNewActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelProgressDialog();
        ActivityCollector.removeActivity(this);
        dismissLoadingWindow();
        if (mPresenter != null)
            mPresenter.detachView();
        mBind.unbind();
    }
}
