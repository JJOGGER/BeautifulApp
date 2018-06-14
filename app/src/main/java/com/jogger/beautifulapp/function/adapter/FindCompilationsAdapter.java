package com.jogger.beautifulapp.function.adapter;


import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.entity.Album;

import java.util.List;

public class FindCompilationsAdapter extends BaseQuickAdapter<Album, BaseViewHolder> {
    public FindCompilationsAdapter(@Nullable List<Album> data) {
        super(R.layout.rv_find_compilation_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Album item) {
        Glide.with(mContext)
                .load(item.getCover_image())
                .into((ImageView) helper.getView(R.id.iv_content));
        helper.setText(R.id.tv_title, item.getTitle());
//        helper.setText(R.id.tv_desc,item.getDigest());
//        helper.setText(R.id.tv_title,item.getTitle());
//        helper.setText(R.id.tv_sub_title,item.getSub_title());
//        Glide.with(mContext)
//                .load(item.getCover_image())
//                .into((ImageView) helper.getView(R.id.iv_content));
//        helper.setText(R.id.tv_desc,item.getDigest());
//        helper.setText(R.id.tv_tags,String.format(mContext.getString(R.string.app_tags_format),
// item.getTags()));
    }
}
