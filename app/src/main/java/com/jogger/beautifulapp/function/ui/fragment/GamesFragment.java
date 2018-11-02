package com.jogger.beautifulapp.function.ui.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.AppInfoData;
import com.jogger.beautifulapp.function.adapter.DialyViewpagerAdapter;
import com.jogger.beautifulapp.function.contract.GameContract;
import com.jogger.beautifulapp.function.presenter.GamePresenter;
import com.jogger.beautifulapp.function.ui.activity.MainActivity;
import com.jogger.beautifulapp.util.AnimatorUtil;
import com.jogger.beautifulapp.util.L;
import com.jogger.beautifulapp.util.SPUtil;
import com.jogger.beautifulapp.widget.rhythm.IRhythmItemListener;
import com.jogger.beautifulapp.widget.rhythm.RhythmAdapter;
import com.jogger.beautifulapp.widget.rhythm.RhythmLayout;
import com.jogger.refreshlayout.PullToRefreshBase;
import com.jogger.refreshlayout.PullToRefreshViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jogger on 2018/6/10.游戏推荐
 */

public class GamesFragment extends BaseFragment<GamePresenter> implements GameContract.View,
        IRhythmItemListener, ViewPager.OnPageChangeListener, PullToRefreshBase
                .OnRefreshListener<ViewPager> {
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.rhythm_layout)
    RhythmLayout rhythmLayout;
    @BindView(R.id.vp_content)
    PullToRefreshViewPager vpContent;
    @BindView(R.id.tv_nice_title)
    TextView tvNiceTitle;
    @BindView(R.id.ll_date)
    LinearLayout llDate;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    private int mCurrentPage = 1;
    private int mPageSize = 20;
    private List<AppInfo> mAppInfos;
    private RhythmAdapter mAdapter;
    private int mPreColor;
    private boolean mIsFirstLoad = true;
    private DialyViewpagerAdapter mViewpagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dialy;
    }

    @Override
    protected GamePresenter createPresenter() {
        return new GamePresenter();
    }

    @Override
    public void init() {
        mPresenter.getGameDatas(mCurrentPage, mPageSize);
        tvNiceTitle.setText(String.format(getString(R.string.nice_title_format), getString(R
                .string.today)));
        //设置钢琴布局的高度 高度为钢琴布局item的宽度+10dp
        int height = (int) rhythmLayout.getRhythmItemWidth() + (int) TypedValue.applyDimension(1,
                10.0F, getResources().getDisplayMetrics());
        ViewGroup.LayoutParams layoutParams = rhythmLayout.getLayoutParams();
        layoutParams.height = height;
        rhythmLayout.setLayoutParams(layoutParams);
        rhythmLayout.setRhythmListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPreColor != 0 && isVisible())
            setBgColor(mPreColor);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        onAppPagerChange(position);
        tvNiceTitle.setVisibility((position == 0) ? View.VISIBLE : View.GONE);
        llDate.setVisibility((position == 0) ? View.GONE : View.VISIBLE);
        tvDate.setVisibility((position == 0) ? View.GONE : View.VISIBLE);
        if (position == 0)
            tvNiceTitle.setText(String.format(getString(R.string.game_title_format), getString(R
                    .string.today)));
        else mPresenter.updateDate(mAppInfos.get(position).getPublish_date());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
    public void getGameDatasSuccess(AppInfoData appData) {
        mAppInfos = appData.getApps();
        if (!mIsFirstLoad) {
            vpContent.onRefreshComplete();
            vpContent.getRefreshableView().setCurrentItem(0);
            mViewpagerAdapter.notifyDataSetChanged();
            return;
        }
        mIsFirstLoad = false;
        int color = Color.parseColor(mAppInfos.get(0)
                .getAuthor_bgcolor());
        ((MainActivity) mActivity).getBaseContainer().setBackgroundColor(color);
        setBgColor(color);
        mAdapter = new RhythmAdapter(mActivity, rhythmLayout, mAppInfos);
        rhythmLayout.setAdapter(mAdapter);
        ViewPager vp = vpContent.getRefreshableView();
        vp.setOffscreenPageLimit(3);
        mViewpagerAdapter = new DialyViewpagerAdapter
                (getChildFragmentManager(), mAppInfos);
        vp.setAdapter(mViewpagerAdapter);
        vp.addOnPageChangeListener(this);
        vpContent.setOnRefreshListener(this);
        rhythmLayout.setScrollRhythmStartDelayTime(400);
        vp.setCurrentItem(0);
        onPageSelected(0);
    }

    @Override
    public void updateDate(int weekResId, String month, int day) {
        //更新日期
        tvWeek.setText(weekResId);
        tvMonth.setText(month);
        tvDate.setText(String.valueOf(day));
    }

    @Override
    public void onRefresh(PullToRefreshBase<ViewPager> refreshView) {
        mPresenter.getGameDatas(1, 20);
    }

    @OnClick({R.id.iv_title, R.id.tv_date})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title:
                ((MainActivity) mActivity).getSlidingMenu().toggle();
                break;
            case R.id.tv_date:
                vpContent.getRefreshableView().setCurrentItem(0);
                break;
        }
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
        setBgColor(color);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mIsViewCreated) {
            int color = SPUtil.getInstance().getInt(Constant.GAME_LAST_COLOR);
            L.e("----------setUserVisibleHint:" + color);
            if (color != 0)
                setBgColor(color);
        }
    }

    private void setBgColor(int color) {
        llContainer.setBackgroundColor(color);
        ((MainActivity) mActivity).getBaseContainer().setBackgroundColor(color);
        mPreColor = color;
        SPUtil.getInstance().put(Constant.GAME_LAST_COLOR, color);
        SPUtil.getInstance().put(Constant.CATEGORY_LAST_COLOR, color);
    }
}
