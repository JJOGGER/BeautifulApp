package com.jogger.beautifulapp.function.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.User;
import com.jogger.beautifulapp.entity.UserHomeInfo;
import com.jogger.beautifulapp.function.contract.UserHomeContract;
import com.jogger.beautifulapp.function.presenter.UserHomePresenter;
import com.jogger.beautifulapp.function.ui.fragment.UserHomeCollectFragment;
import com.jogger.beautifulapp.function.ui.fragment.UserHomeRecommendFragment;
import com.jogger.beautifulapp.swipelayoutlib.app.SwipeBackActivity;
import com.jogger.beautifulapp.util.L;
import com.jogger.beautifulapp.widget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

//美友主页
public class UserHomeActivity extends SwipeBackActivity<UserHomePresenter> implements UserHomeContract.View, ViewPager.OnPageChangeListener {
    private static final int RECOMMAND_PAGE = 0;
    @BindView(R.id.immersive)
    RelativeLayout rlMain;
    @BindView(R.id.cl_header)
    ConstraintLayout clHeader;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;
    @BindView(R.id.iv_icon)
    CircleImageView ivIcon;
    @BindView(R.id.cl_top)
    ConstraintLayout clTop;
    @BindView(R.id.tv_recommend)
    TextView tvRecommend;
    @BindView(R.id.tv_collection)
    TextView tvCollection;
    @BindView(R.id.v_tab)
    View vTab;
    @BindView(R.id.cl_tab)
    ConstraintLayout clTab;
    private boolean mIsTransCompleted;//标记头部是否已隐藏（true）
    private int mMoveHeight;
    private ValueAnimator mValueAnimator, mValueAnimator2;
    private User mUser;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_home;
    }


    @Override
    protected IPresenter createPresenter() {
        return new UserHomePresenter();
    }

    @Override
    protected void init() {
        super.init();
        mUser = (User) getIntent().getSerializableExtra(Constant.USER_INFO);
        if (mUser == null) return;
        mPresenter.getUserHomeInfo(mUser.getId());
        tvTitle.setText(mUser.getName());
        tvSubTitle.setText(mUser.getCareer());
        Glide.with(this)
                .load(mUser.getAvatar_url())
                .into(ivIcon);
        L.e("------mUser.getBg_color()："+mUser.getBg_color());
        rlMain.setBackgroundColor(Color.parseColor(mUser.getBg_color()));
        vpContent.addOnPageChangeListener(this);
        vpContent.setOffscreenPageLimit(2);
        clHeader.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            private int mOriTopMargin;

            @Override
            public void onGlobalLayout() {
                //伸展动画
                clHeader.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)
                        clHeader.getLayoutParams();
                mOriTopMargin = layoutParams.topMargin;
                mMoveHeight = clTop.getMeasuredHeight();
                mValueAnimator = ValueAnimator.ofInt(-mMoveHeight, mOriTopMargin);
                mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        clTop.setVisibility(View.VISIBLE);
                        int margin = (int) valueAnimator.getAnimatedValue();
                        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)
                                clHeader.getLayoutParams();
                        layoutParams.topMargin = margin;
                        clHeader.setLayoutParams(layoutParams);
                        clHeader.postInvalidate();
//                        clTab.animate().scaleX(1).start();
                        clTab.setScaleX(0.8f + valueAnimator.getAnimatedFraction() * 0.2f);
                        clTab.setScaleY(0.8f + valueAnimator.getAnimatedFraction() * 0.2f);
                    }
                });
                mValueAnimator.setDuration(300);
                mValueAnimator.setRepeatCount(0);
                mValueAnimator2 = ValueAnimator.ofInt(mOriTopMargin, -mMoveHeight);
                mValueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)
                                clHeader.getLayoutParams();
                        layoutParams.topMargin = (int) valueAnimator.getAnimatedValue();
                        clHeader.setLayoutParams(layoutParams);
                        clTab.setScaleX(1 - 0.2f * valueAnimator.getAnimatedFraction());
                        clTab.setScaleY(1 - 0.2f * valueAnimator.getAnimatedFraction());
                    }
                });
                mValueAnimator2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        clTop.setVisibility(View.INVISIBLE);
                    }
                });
                mValueAnimator2.setDuration(200);
                mValueAnimator2.setRepeatCount(0);

            }
        });
    }

    public void updateCount(int commandCount, int collectionCount) {
        if (commandCount != -1) {
            String recommand = String.format(getString(R.string.recommend_format), commandCount);
            SpannableString spannableRecommand = new SpannableString(recommand);
            spannableRecommand.setSpan(new TextAppearanceSpan(this, R.style.UserTabTextStyle1),
                    0, 2,
                    Spannable
                            .SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableRecommand.setSpan(new TextAppearanceSpan(this, R.style.UserTabTextStyle2), 2,
                    recommand
                            .length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvRecommend.setText(spannableRecommand);
        }
        if (collectionCount != -1) {
            String collection = String.format(getString(R.string.collection_format), collectionCount);

            SpannableString spannableCollection = new SpannableString(collection);
            spannableCollection.setSpan(new TextAppearanceSpan(this, R.style.UserTabTextStyle1), 0,
                    2, Spannable
                            .SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableCollection.setSpan(new TextAppearanceSpan(this, R.style.UserTabTextStyle2), 2,
                    collection.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvCollection.setText(spannableCollection);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (position == RECOMMAND_PAGE) {
            vTab.animate().setInterpolator(new OvershootInterpolator()).translationX(0).start();
        } else {
            vTab.animate().setInterpolator(new OvershootInterpolator()).translationX(vTab
                    .getMeasuredWidth()).start();
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void scrollToTop(boolean isTop, int dy) {
        if (isTop && dy < 0) {
            if (mValueAnimator == null || mValueAnimator.isRunning() || mValueAnimator2.isRunning
                    () || !mIsTransCompleted)
                return;
            //执行伸展动画
            mValueAnimator.start();
            mIsTransCompleted = false;
        } else {
            if (mValueAnimator == null || mValueAnimator.isRunning() || mValueAnimator2.isRunning
                    () || mIsTransCompleted)
                return;
            if (dy > 0) {
                //执行隐藏动画
                mValueAnimator2.start();
                mIsTransCompleted = true;
            }
        }
    }

    @OnClick({R.id.ibtn_return, R.id.tv_recommend, R.id.tv_collection})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_return:
                finish();
                break;
            case R.id.tv_recommend:
                vpContent.setCurrentItem(0);
                break;
            case R.id.tv_collection:
                vpContent.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void getUserHomeInfoSuccess(UserHomeInfo userHomeInfo) {
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), userHomeInfo.getUp_counts());
        vpContent.setAdapter(adapter);
        updateCount(userHomeInfo.getApp_counts(), userHomeInfo.getCollect_counts());
    }

    private class MyAdapter extends FragmentPagerAdapter {
        private int mUpCounts;

        MyAdapter(FragmentManager fm, int up_counts) {
            super(fm);
            mUpCounts = up_counts;
        }

        @Override
        public Fragment getItem(int position) {
            BaseFragment baseFragment;
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.USER_INFO, mUser);
            if (position == RECOMMAND_PAGE) {
                baseFragment = new UserHomeRecommendFragment();
                bundle.putInt(Constant.FLOWERS, mUpCounts);
            } else {
                baseFragment = new UserHomeCollectFragment();
            }
            baseFragment.setArguments(bundle);
            return baseFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
