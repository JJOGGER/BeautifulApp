package com.jogger.beautifulapp.function.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.jeremyfeinstein.slidingmenu.SlidingMenu;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.function.adapter.FindViewpagerAdapter;
import com.jogger.beautifulapp.function.contract.FindContract;
import com.jogger.beautifulapp.function.presenter.FindPresenter;
import com.jogger.beautifulapp.function.ui.activity.MainActivity;

import java.lang.reflect.Field;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/10.发现
 */

public class FindFragment extends BaseFragment<FindPresenter> implements FindContract.View,
        ViewPager.OnPageChangeListener {
    @BindView(R.id.tl_tab)
    TabLayout tlTab;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void init() {
        vpContent.setOffscreenPageLimit(3);
        vpContent.setAdapter(new FindViewpagerAdapter(getChildFragmentManager()));
        tlTab.setupWithViewPager(vpContent);
        setIndicator(tlTab);
        vpContent.addOnPageChangeListener(this);
    }

    @Override
    protected FindPresenter createPresenter() {
        return new FindPresenter();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            ((MainActivity) mActivity).getSlidingMenu().setTouchModeAbove(SlidingMenu
                    .TOUCHMODE_FULLSCREEN);
        } else {
            ((MainActivity) mActivity).getSlidingMenu().setTouchModeAbove(SlidingMenu
                    .TOUCHMODE_MARGIN);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setIndicator(TabLayout tabs) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert tabStrip != null;
        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int left = (int) (getResources().getDisplayMetrics().density * 10);
        int right = (int) (getResources().getDisplayMetrics().density * 10);
        assert ll_tab != null;
        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout
                    .LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
