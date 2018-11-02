package com.jogger.beautifulapp.function.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.entity.CompilationDesc;

import java.util.List;

/**
 * Created by jogger on 2018/10/29.
 */
public class FindCompilationDescAdapter extends BaseQuickAdapter<CompilationDesc, BaseViewHolder> {
    public FindCompilationDescAdapter(@Nullable List<CompilationDesc> data) {
        super(R.layout.rv_find_compilation_desc_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CompilationDesc item) {
        Glide.with(mContext)
                .load(item.getIcon_image())
                .into((ImageView) helper.getView(R.id.iv_title));
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_desc, item.getDigest());

        if (item.getShare_times() == 0) {
            helper.setText(R.id.tv_share_hint, "暂无美友分享");
            helper.setText(R.id.tv_view_desc, "我来分享");
            helper.setTag(R.id.tv_view_desc, 1);
        } else {
            helper.setText(R.id.tv_share_hint, item.getShare_times() + "位美友分享");
            helper.setText(R.id.tv_view_desc, "查看详情");
            helper.setTag(R.id.tv_view_desc, 0);
        }
        helper.getView(R.id.tv_flowers).setVisibility(item.getUp_times() == 0 ? View.GONE : View.VISIBLE);
        if (item.getUp_times() != 0) {
            helper.setText(R.id.tv_flowers, String.valueOf(item.getUp_times()));
        }
        helper.addOnClickListener(R.id.tv_download);
    }
}
