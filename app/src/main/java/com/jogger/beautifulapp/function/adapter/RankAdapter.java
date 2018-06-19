package com.jogger.beautifulapp.function.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.entity.SocialArticle;

import java.util.List;


public class RankAdapter extends BaseQuickAdapter<SocialArticle, BaseViewHolder> {
    public RankAdapter(@Nullable List<SocialArticle> data) {
        super(R.layout.rv_rank_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SocialArticle item) {
        helper.setText(R.id.tv_no, String.valueOf(helper.getAdapterPosition() + 1));
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_sub_title, item.getSub_title());
        Glide.with(mContext)
                .load(item.getIcon_image())
                .into((ImageView) helper.getView(R.id.iv_icon));
    }
}
