package com.jogger.beautifulapp.function.adapter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.MediaArticle;
import com.jogger.beautifulapp.function.ui.fragment.FindRoundHeaderFragment;

import java.util.List;

public class FindRoundAdapter extends BaseQuickAdapter<MediaArticle, BaseViewHolder> implements
        ViewPager.OnPageChangeListener {

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
//        helper.setText(R.id.tv_title, item.getTitle());
//        helper.setText(R.id.tv_sub_title, item.getSub_title());
//        helper.setText(R.id.tv_up_times, String.valueOf(item.getUp_times()));
//        Glide.with(mContext)
//                .load(item.getCover_image())
//                .into((ImageView) helper.getView(R.id.iv_content));
    }

    public void setHeaderDatas(@Nullable List<MediaArticle> datas) {
        HeaderAdapter adapter = (HeaderAdapter) ((ViewPager) getHeaderLayout().getChildAt(0)
                .findViewById(R.id.vp_header))
                .getAdapter();
        if (adapter != null) adapter.loadDatas(datas);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

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

        public void loadDatas(List<MediaArticle> mediaArticles) {
            mMediaArticles = mediaArticles;
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
