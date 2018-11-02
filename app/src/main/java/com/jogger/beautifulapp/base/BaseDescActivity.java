package com.jogger.beautifulapp.base;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.function.adapter.DescBottomPagerAdapter;
import com.jogger.beautifulapp.swipelayoutlib.app.SwipeBackActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jogger on 2018/10/27.
 */
public abstract class BaseDescActivity<T> extends SwipeBackActivity implements DescBottomPagerAdapter.OnDescBottomClickListener
        , NestedScrollView.OnScrollChangeListener {
    private static final int REQUEST_INSTALL_PACKAGES_CODE = 0x0a;
    private static final int GET_UNKNOWN_APP_SOURCES = 0x0b;
    private static final int MAX_SCROLL_OFFSET = 5;
    @BindView(R.id.nsv_content)
    public NestedScrollView nsvContent;
    @BindView(R.id.vp_bottom)
    ViewPager vpBottom;
    @BindView(R.id.rl_roll_menu)
    RelativeLayout rlRollMenu;
    @BindView(R.id.rl_menu)
    RelativeLayout rlMenu;
    @BindView(R.id.fl_top_container)
    FrameLayout flTopContainer;
    @BindView(R.id.ibtn_download)
    ImageButton ibtnDownload;
    @BindView(R.id.tv_download)
    TextView tvDownload;
    @BindView(R.id.ibtn_app)
    ImageButton ibtnApp;
    protected DescBottomPagerAdapter mBottomPagerAdapter;
    protected int mShowY = -1;//设置隐藏动画的按钮所处位置
    private boolean mFloatBtnShow = true;
    protected T mPresenter;


    @OnClick({R.id.ibtn_return, R.id.ibtn_collection, R.id.tv_collection,
            R.id.ibtn_share, R.id.tv_share, R.id.ibtn_download, R.id.tv_download})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_return:
                finish();
                break;
            case R.id.ibtn_collection:
            case R.id.tv_collection:
                break;
            case R.id.ibtn_share:
            case R.id.tv_share:
                break;
            case R.id.ibtn_download:
            case R.id.tv_download:
                download();
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        int[] postion = new int[2];
        rlRollMenu.getLocationOnScreen(postion);
        //隐藏位置：scrollview中按钮y-头部位置bottom+头部位置高度一半
        if (mShowY == -1)
            mShowY = postion[1] - flTopContainer.getBottom() + flTopContainer
                    .getMeasuredHeight() / 2;
    }

    protected void setDownloadEnable(boolean enable) {
        if (!enable) {
            ibtnDownload.setVisibility(View.GONE);
            tvDownload.setVisibility(View.GONE);
        }
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int
            oldScrollY) {
        if (scrollY >= mShowY) {
            rlRollMenu.setVisibility(View.INVISIBLE);
            rlMenu.setVisibility(View.VISIBLE);
        } else if (scrollY < mShowY) {
            if (rlMenu.getVisibility() == View.VISIBLE) {
                rlRollMenu.setVisibility(View.VISIBLE);
                rlMenu.setVisibility(View.INVISIBLE);
            }
        }

        if (scrollY - oldScrollY >= MAX_SCROLL_OFFSET) {
            //表示上移
            if (mFloatBtnShow) {
                ibtnApp.animate().translationY(500).setDuration(300)
                        .setInterpolator(new OvershootInterpolator()).start();
                mFloatBtnShow = false;
            }
        } else if (oldScrollY - scrollY >= MAX_SCROLL_OFFSET) {
            if (!mFloatBtnShow) {
                mFloatBtnShow = true;
                ibtnApp.animate().translationY(0).setDuration(300)
                        .setInterpolator(new OvershootInterpolator()).start();
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void init() {
        super.init();
        mPresenter= (T) super.mPresenter;
        nsvContent.setOnScrollChangeListener(this);
        initBottom();
    }

    private void initBottom() {
        vpBottom.setOffscreenPageLimit(2);
        mBottomPagerAdapter = new DescBottomPagerAdapter(this, vpBottom);
        mBottomPagerAdapter.setOnDescBottomClickListener(this);
        vpBottom.setAdapter(mBottomPagerAdapter);
    }

    protected abstract void download();

    @Override
    public void onFlower() {

    }

    @Override
    public void onLeaf() {

    }

    @Override
    public void onDownload() {
        download();
    }

}
