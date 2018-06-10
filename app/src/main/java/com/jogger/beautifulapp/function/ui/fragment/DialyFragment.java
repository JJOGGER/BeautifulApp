package com.jogger.beautifulapp.function.ui.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.entity.AppData;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.function.adapter.DialyViewpagerAdapter;
import com.jogger.beautifulapp.function.contract.DialyContract;
import com.jogger.beautifulapp.function.model.DialyModel;
import com.jogger.beautifulapp.function.presenter.DialyPresenter;
import com.jogger.beautifulapp.function.ui.activity.MainActivity;
import com.jogger.beautifulapp.util.AnimatorUtil;
import com.jogger.beautifulapp.widget.rhythm.IRhythmItemListener;
import com.jogger.beautifulapp.widget.rhythm.RhythmAdapter;
import com.jogger.beautifulapp.widget.rhythm.RhythmLayout;
import com.jogger.refreshlayout.PullToRefreshViewPager;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/10.
 * 每日最美
 */

public class DialyFragment extends BaseFragment<DialyPresenter> implements DialyContract.View,
        IRhythmItemListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.rhythm_layout)
    RhythmLayout rhythmLayout;
    @BindView(R.id.vp_content)
    PullToRefreshViewPager vpContent;
    private int mCurrentPage = 1;
    private int mPageSize = 20;
    private List<AppInfo> mAppInfos;
    private RhythmAdapter mAdapter;
    private int mPreColor;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dialy;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new DialyPresenter(new DialyModel());
    }

    @Override
    public void init() {
        mPresenter.getDialyDatas(mCurrentPage, mPageSize);
        //设置钢琴布局的高度 高度为钢琴布局item的宽度+10dp
        int height = (int) rhythmLayout.getRhythmItemWidth() + (int) TypedValue.applyDimension(1,
                10.0F, getResources().getDisplayMetrics());
        ViewGroup.LayoutParams layoutParams = rhythmLayout.getLayoutParams();
        layoutParams.height = height;
        rhythmLayout.setLayoutParams(layoutParams);
        rhythmLayout.setRhythmListener(this);
    }

    @Override
    public void loadDatas(AppData appData) {
        mAppInfos = appData.getApps();
        int color = Color.parseColor(mAppInfos.get(0)
                .getAuthor_bgcolor());
        ((MainActivity) mActivity).setBackgroundColor(color);
        llContainer.setBackgroundColor(color);
        mPreColor = color;
        mAdapter = new RhythmAdapter(mActivity, rhythmLayout, mAppInfos);
        rhythmLayout.setAdapter(mAdapter);
        ViewPager vp = vpContent.getRefreshableView();
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new DialyViewpagerAdapter(getChildFragmentManager(), mAppInfos));
        vp.addOnPageChangeListener(this);
        rhythmLayout.setScrollRhythmStartDelayTime(400);
    }

    /**
     * 改变当前选中钢琴按钮
     *
     * @param position viewPager的位置
     */
    private void onAppPagerChange(int position) {
        //执行动画，改变升起的钢琴按钮
        if (rhythmLayout != null)
            rhythmLayout.showRhythmAtPosition(position);
//        toggleRocketBtn(position);
        //得到当前的背景颜色
        String bgcolor = mAppInfos.get(position).getRecommanded_background_color();
        int color = Color.parseColor(bgcolor);
        //执行颜色转换动画
        AnimatorUtil.showBackgroundColorAnimation(((MainActivity) mActivity).getBaseContainer(),
                mPreColor, color, 400);
        llContainer.setBackgroundColor(color);
        mPreColor = color;
    }

    @Override
    public void onRhythmItemChanged(int paramInt) {

    }

    @Override
    public void onSelected(int paramInt) {
        vpContent.getRefreshableView().setCurrentItem(paramInt);
    }

    @Override
    public void onStartSwipe() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        onAppPagerChange(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
