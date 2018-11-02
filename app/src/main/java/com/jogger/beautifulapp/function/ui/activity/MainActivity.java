package com.jogger.beautifulapp.function.ui.activity;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.SlidingMenu;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseActivity;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.function.contract.MainContract;
import com.jogger.beautifulapp.function.presenter.MainPresenter;
import com.jogger.beautifulapp.function.ui.fragment.CategoryFragment;
import com.jogger.beautifulapp.function.ui.fragment.DialyFragment;
import com.jogger.beautifulapp.function.ui.fragment.FindFragment;
import com.jogger.beautifulapp.function.ui.fragment.GamesFragment;
import com.jogger.beautifulapp.function.ui.fragment.RankFragment;
import com.jogger.beautifulapp.util.T;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.cl_main)
    ConstraintLayout clMain;
    @BindView(R.id.sm_menu)
    SlidingMenu smMenu;
    @BindView(R.id.v_find)
    View vFind;
    @BindView(R.id.v_dialy)
    View vDialy;
    @BindView(R.id.v_category)
    View vCategory;
    @BindView(R.id.v_game)
    View vGame;
    @BindView(R.id.v_rank)
    View vRank;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private FragmentManager mFragmentManager;
    private Fragment mShowFragment;
    private ExitTimerTask mExitTimerTask;
    private Timer mExitTimer;
    private boolean mIsExit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void init() {
        super.init();
        mPresenter.initSlidingMenuAnim();
        mFragmentManager = getSupportFragmentManager();
        initFragment();
        smMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        checkStoragePermission();
    }

    private void initFragment() {
        showFragment(DialyFragment.class);
    }

    public SlidingMenu getSlidingMenu() {
        return smMenu;
    }

    public ConstraintLayout getBaseContainer() {
        return clMain;
    }

    @Override
    public void initSlidingMenuAnim(SlidingMenu.CanvasTransformer transformer) {
        smMenu.setBehindCanvasTransformer(transformer);
    }

    @Override
    public void getRandomDatasSuccess(Object obje) {

    }

//    @Override
//    public void getRandomDatasSuccess(RecentAppData recentAppData) {
//        if ("community".equals(recentAppData.getType())){
////            startNewActivity(Rec);
//        }
//    }

    @OnClick({R.id.tv_find, R.id.tv_dialy, R.id.tv_category, R.id.tv_games, R.id.tv_rank,
            R.id.ibtn_search, R.id.tv_join_party, R.id.ibtn_download_manage})
    public void OnMenuClick(View v) {
        showDot(v.getId());
        switch (v.getId()) {
            case R.id.tv_find:
                showFragment(FindFragment.class);
                smMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.tv_dialy:
                showFragment(DialyFragment.class);
                smMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                break;
            case R.id.tv_category:
                showFragment(CategoryFragment.class);
                smMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.tv_games:
                showFragment(GamesFragment.class);
                smMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                break;
            case R.id.tv_rank:
                showFragment(RankFragment.class);
                smMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.ibtn_search:
                Intent intent = new Intent(this, SearchActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
            case R.id.tv_join_party:
                break;
            case R.id.ibtn_download_manage:
                startNewActivity(DownloadManageActivity.class);
                break;
        }
        smMenu.toggle();
    }

    @Override
    public void onBackPressed() {
        if (!smMenu.isMenuShowing())
            smMenu.toggle();
        else {
            if (mExitTimer == null) {
                mExitTimer = new Timer();
                mExitTimerTask = new ExitTimerTask();
            }
            if (!mIsExit) {
                mIsExit = true;
                T.show(R.string.exit_msg);
                mExitTimer.schedule(mExitTimerTask, 1000);
            } else {
                finish();
            }
        }
    }

    private void checkStoragePermission() {

        try {
            int permission = ActivityCompat.checkSelfPermission(this,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示fragment
     */
    private <F extends BaseFragment> void showFragment(Class<F> cls) {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mShowFragment != null) {//获取显示的fragment，如果有，则隐藏
            transaction.hide(mShowFragment);
        }
        //通过传进来的fragment的类名作为tag查看fragment是否存在
        mShowFragment = mFragmentManager.findFragmentByTag(cls.getName());
        if (mShowFragment != null) {
            //存在则显示
            transaction.show(mShowFragment);
        } else {
            //不存在则添加一个
            try {
                Class<?> clazz = Class.forName(cls.getName());
                try {
                    BaseFragment fragment = (BaseFragment) clazz.newInstance();
                    transaction.add(R.id.fl_main_container, fragment,
                            cls.getName());
                    mShowFragment = fragment;//并将当前显示frament设为添加的fragment
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        mShowFragment.setUserVisibleHint(true);//手动触发可见方法
        transaction.commitAllowingStateLoss();
    }
    private void showDot(int viewId) {
        switch (viewId) {
            case R.id.tv_find:
                vDialy.setVisibility(View.INVISIBLE);
                vCategory.setVisibility(View.INVISIBLE);
                vGame.setVisibility(View.INVISIBLE);
                vRank.setVisibility(View.INVISIBLE);
                vFind.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_dialy:
                vDialy.setVisibility(View.VISIBLE);
                vCategory.setVisibility(View.INVISIBLE);
                vGame.setVisibility(View.INVISIBLE);
                vRank.setVisibility(View.INVISIBLE);
                vFind.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_category:
                vDialy.setVisibility(View.INVISIBLE);
                vCategory.setVisibility(View.VISIBLE);
                vGame.setVisibility(View.INVISIBLE);
                vRank.setVisibility(View.INVISIBLE);
                vFind.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_games:
                vDialy.setVisibility(View.INVISIBLE);
                vCategory.setVisibility(View.INVISIBLE);
                vGame.setVisibility(View.VISIBLE);
                vRank.setVisibility(View.INVISIBLE);
                vFind.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_rank:
                vDialy.setVisibility(View.INVISIBLE);
                vCategory.setVisibility(View.INVISIBLE);
                vGame.setVisibility(View.INVISIBLE);
                vRank.setVisibility(View.VISIBLE);
                vFind.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mExitTimer != null) {
            mExitTimer.cancel();
            mExitTimer = null;
        }
        if (mExitTimerTask != null) {
            mExitTimerTask.cancel();
            mExitTimerTask = null;
        }

    }

    private class ExitTimerTask extends TimerTask {
        @Override
        public void run() {
            mIsExit = false;
            mExitTimer.cancel();
            mExitTimerTask.cancel();
            mExitTimer = null;
            mExitTimerTask = null;
        }
    }
}
