package com.jogger.beautifulapp.function.ui.activity;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.SlidingMenu;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseActivity;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.function.contract.MainContract;
import com.jogger.beautifulapp.function.presenter.MainPresenter;
import com.jogger.beautifulapp.function.ui.fragment.CategoryFragment;
import com.jogger.beautifulapp.function.ui.fragment.DialyFragment;
import com.jogger.beautifulapp.function.ui.fragment.FindFragment;
import com.jogger.beautifulapp.function.ui.fragment.GamesFragment;
import com.jogger.beautifulapp.function.ui.fragment.RankFragment;
import com.jogger.beautifulapp.util.L;
import com.jogger.beautifulapp.util.SPUtil;
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

    @OnClick({R.id.tv_find, R.id.tv_dialy, R.id.tv_category, R.id.tv_games, R.id.tv_rank})
    public void OnMenuClick(View v) {
        showDot(v.getId());
        switch (v.getId()) {
            case R.id.tv_find:
                showFragment(FindFragment.class);
                smMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                clMain.setBackgroundColor(getResources().getColor(R.color.colorFind));
                break;
            case R.id.tv_dialy:
                showFragment(DialyFragment.class);
                smMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                L.e("---------DIALY_LAST_COLOR" + SPUtil.getInstance().getInt(Constant
                        .DIALY_LAST_COLOR));
                clMain.setBackgroundColor(SPUtil.getInstance().getInt(Constant.DIALY_LAST_COLOR,
                        getResources().getColor(R.color.colorAccent)));
                break;
            case R.id.tv_category:
                showFragment(CategoryFragment.class);
                smMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                clMain.setBackgroundColor(getResources().getColor(R.color.colorCategory));
                break;
            case R.id.tv_games:
                showFragment(GamesFragment.class);
                smMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                clMain.setBackgroundColor(SPUtil.getInstance().getInt(Constant.DIALY_LAST_COLOR,
                        getResources().getColor(R.color.colorAccent)));
                break;
            case R.id.tv_rank:
                showFragment(RankFragment.class);
                smMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                clMain.setBackgroundColor(getResources().getColor(R.color.colorFind));
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
            L.e("----------mIsExit:" + mIsExit);
            if (!mIsExit) {
                mIsExit = true;
                T.show(R.string.exit_msg);
                mExitTimer.schedule(mExitTimerTask, 1000);
            } else {
                finish();
            }
        }
    }

    /**
     * 显示fragment
     */
    private <T extends BaseFragment> void showFragment(Class<T> cls) {
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
            L.e("----------ExitTimerTask:" + mIsExit);
            mExitTimer.cancel();
            mExitTimerTask.cancel();
            mExitTimer = null;
            mExitTimerTask = null;
        }
    }
}
