package com.jogger.beautifulapp.function.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.entity.Collection;

import java.util.List;


public class UserCollectAdapter extends BaseQuickAdapter<Collection, BaseViewHolder> {
    public UserCollectAdapter(@Nullable List<Collection> data) {
        super(R.layout.rv_user_collect_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Collection item) {
        Glide.with(mContext)
                .load(item.getIcon())
                .into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_sub_title, item.getSource_msg());
        helper.setText(R.id.tv_flowers, String.valueOf(item.getUp_times()));
        helper.setText(R.id.tv_commons, String.valueOf(item.getDown_times()));
    }
}
