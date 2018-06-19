package com.jogger.beautifulapp.function.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.entity.User;

import java.util.List;


public class FindNiceFriendItemAdapter extends BaseQuickAdapter<User, BaseViewHolder> {
    FindNiceFriendItemAdapter(@Nullable List<User> data) {
        super(R.layout.rv_find_nice_friend_item_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {
        helper.setText(R.id.tv_no, String.valueOf(helper.getAdapterPosition() + 1));
        Glide.with(mContext)
                .load(item.getAvatar_url())
                .into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_title, item.getName());
        helper.setText(R.id.tv_sub_title, item.getCareer());
        helper.setText(R.id.tv_flowers, "+" + item.getFlowers());
    }
}
