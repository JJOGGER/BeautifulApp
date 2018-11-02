package com.jogger.beautifulapp.function.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.entity.TopApp;

import java.util.List;

/**
 * Created by jogger on 2018/11/1.
 */
public class SearchItemAdapter extends BaseQuickAdapter<TopApp, BaseViewHolder> {

    public SearchItemAdapter(@Nullable List<TopApp> data) {
        super(R.layout.rv_search_more_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopApp item) {
        if (!TextUtils.isEmpty(item.getIcon_url())) {
            Glide.with(mContext)
                    .load(item.getIcon_url())
                    .into((ImageView) helper.getView(R.id.iv_icon));
        }
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_sub_title, item.getSub_title());
    }
}
