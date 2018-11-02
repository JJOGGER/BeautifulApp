package com.jogger.beautifulapp.function.adapter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.MediaArticle;
import com.jogger.beautifulapp.function.ui.fragment.FindRoundHeaderFragment;
import com.jogger.beautifulapp.util.SizeUtil;

import java.util.List;

public class FindRoundAdapter extends BaseQuickAdapter<MediaArticle, BaseViewHolder> implements
        ViewPager.OnPageChangeListener {

    private final ImageView mIvDotPressed;
    private int mPointDis;
    private final LinearLayout mLlPointContainer;

    public FindRoundAdapter(BaseFragment context, @Nullable List<MediaArticle> data) {
        super(R.layout.rv_find_round_item, data);
        View header = LayoutInflater.from(context.getActivity()).inflate(R.layout
                .rv_find_round_item_header, null);
        addHeaderView(header);
        ViewPager vpHeader = header.findViewById(R.id.vp_header);
        HeaderAdapter adapter = new HeaderAdapter(context.getChildFragmentManager());
        vpHeader.setOffscreenPageLimit(3);
        vpHeader.setAdapter(adapter);
        vpHeader.addOnPageChangeListener(this);
        mIvDotPressed = header.findViewById(R.id.iv_dot_pressed);
        mLlPointContainer = header.findViewById(R.id.ll_point_container);
    }

    @Override
    protected void convert(BaseViewHolder helper, MediaArticle item) {
        Glide.with(mContext)
                .load(item.getIcon_image())
                .into((ImageView) helper.getView(R.id.iv_title));
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_sub_title, item.getSub_title());
        Glide.with(mContext)
                .load(item.getCover_image())
                .into((ImageView) helper.getView(R.id.iv_content));
        helper.setText(R.id.tv_flowers, String.valueOf(item.getUp_times()));
        helper.setText(R.id.tv_desc, item.getDigest());
        helper.setText(R.id.tv_tags, item.getTags().get(0));
    }

    public void setHeaderDatas(@Nullable List<MediaArticle> datas) {
        HeaderAdapter adapter = (HeaderAdapter) ((ViewPager) getHeaderLayout().getChildAt(1)
                .findViewById(R.id.vp_header))
                .getAdapter();
        if (adapter != null) adapter.loadDatas(datas);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 小圆点的移动距离=移动百分比*两个圆点的间距
        // 更新点距离
        int dis = (int) (mPointDis * positionOffset) + position * mPointDis;// 因为移动完一个界面后，百分比会归0，所以要加上移动过的单位position*mPointDis
        //获取小圆点的布局属性，更新左边距
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mIvDotPressed.getLayoutParams();
        params.leftMargin = dis;// 修改左边距
        // 重新设置布局参数
        mIvDotPressed.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class HeaderAdapter extends FragmentPagerAdapter {
        private List<MediaArticle> mMediaArticles;

        HeaderAdapter(FragmentManager fm) {
            super(fm);
        }

        void loadDatas(List<MediaArticle> mediaArticles) {
            mMediaArticles = mediaArticles;
            if (mediaArticles == null || mediaArticles.isEmpty()) return;
            if (mediaArticles.size() > 1)
                for (int i = 0; i < mediaArticles.size(); i++) {
                    //添加默认小圆点
                    ImageView point_default = new ImageView(mContext);
                    point_default.setImageResource(R.drawable.round_dot_n_shape);
                    //初始化布局参数，通过LayoutParams设置自适应宽高
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    if (i > 0) {
                        //第二个圆开始设置左边距
                        params.leftMargin = SizeUtil.dp2px(2);
                    }
                    //设置布局参数
                    point_default.setLayoutParams(params);
                    mLlPointContainer.addView(point_default);
                }
            mLlPointContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mLlPointContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    mPointDis = mLlPointContainer.getChildAt(1).getLeft() - mLlPointContainer.getChildAt(0).getLeft();
                }
            });
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            FindRoundHeaderFragment headerFragment = new FindRoundHeaderFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.MEDIA_ARTICLE, mMediaArticles.get(position));
            headerFragment.setArguments(bundle);
            return headerFragment;
        }

        @Override
        public int getCount() {
            if (mMediaArticles != null)
                return mMediaArticles.size();
            return 0;
        }
    }
}
