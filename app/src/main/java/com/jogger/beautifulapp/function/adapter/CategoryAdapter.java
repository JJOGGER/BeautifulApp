package com.jogger.beautifulapp.function.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.recyclerview.DividerLinearItemDecoration;
import com.jogger.beautifulapp.base.recyclerview.UnScrollLinearLayoutManager;
import com.jogger.beautifulapp.entity.Category;
import com.jogger.beautifulapp.entity.TopApp;

import java.util.List;

public class CategoryAdapter extends BaseQuickAdapter<Category, BaseViewHolder> {
    public CategoryAdapter(@Nullable List<Category> data) {
        super(R.layout.rv_category_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        RecyclerView rvContent = helper.getView(R.id.rv_content);
        rvContent.setLayoutManager(new UnScrollLinearLayoutManager(mContext));
        rvContent.addItemDecoration(new DividerLinearItemDecoration(mContext));
        ItemAdapter adapter = new ItemAdapter(item.getTop_apps());
        rvContent.setAdapter(adapter);
        Glide.with(mContext)
                .load(item.getIcon_url())
                .into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_title, item.getTitle());
    }

    class ItemAdapter extends BaseQuickAdapter<TopApp, BaseViewHolder> {

        ItemAdapter(@Nullable List<TopApp> data) {
            super(R.layout.rv_category_item_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, TopApp item) {
            Glide.with(mContext)
                    .load(item.getIcon_url())
                    .into((ImageView) helper.getView(R.id.iv_icon));
            helper.setText(R.id.tv_title, item.getTitle());
            helper.setText(R.id.tv_sub_title, item.getSub_title());
        }
    }
}
