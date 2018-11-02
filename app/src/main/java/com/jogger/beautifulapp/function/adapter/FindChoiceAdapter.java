package com.jogger.beautifulapp.function.adapter;


import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.entity.MediaArticle;

import java.util.List;

public class FindChoiceAdapter extends BaseQuickAdapter<MediaArticle, BaseViewHolder> {

    public FindChoiceAdapter(@Nullable List<MediaArticle> data) {
        super(R.layout.rv_find_choice_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MediaArticle item) {
        Glide.with(mContext)
                .load(item.getIcon_image())
                .into((ImageView) helper.getView(R.id.iv_title));
        helper.setText(R.id.tv_desc, item.getDigest());
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_sub_title, item.getSub_title());
        helper.setText(R.id.tv_flowers,String.valueOf(item.getUp_times()));
        int adapterPosition = helper.getAdapterPosition();
        if (adapterPosition == 1) {
            helper.setText(R.id.tv_date, R.string.today);
        } else if (adapterPosition == 2) {
            helper.setText(R.id.tv_date, R.string.yesterday);
        } else {
            helper.setText(R.id.tv_date, item.getDate());
        }
        Glide.with(mContext)
                .load(item.getCover_image())
                .into((ImageView) helper.getView(R.id.iv_content));
        helper.setText(R.id.tv_desc, item.getDigest());
        helper.setText(R.id.tv_tags, String.format(mContext.getString(R.string.app_tags_format),
                item.getTags()));

    }

}
