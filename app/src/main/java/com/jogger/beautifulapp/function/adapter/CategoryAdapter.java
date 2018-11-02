package com.jogger.beautifulapp.function.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.recyclerview.DividerLinearItemDecoration;
import com.jogger.beautifulapp.base.recyclerview.UnScrollLinearLayoutManager;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.control.DownloadControl;
import com.jogger.beautifulapp.entity.Category;
import com.jogger.beautifulapp.entity.DownloadMethod;
import com.jogger.beautifulapp.entity.DownloadUrl;
import com.jogger.beautifulapp.entity.TopApp;
import com.jogger.beautifulapp.http.download.DownloadProgressAdapter;
import com.jogger.beautifulapp.util.L;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends BaseQuickAdapter<Category, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    private OnCategoryAdapterClickListener mListener;

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.tv_more) {
            if (mListener != null) {
                mListener.onMore((Category) adapter.getItem(position));
            }
            return;
        }
        TopApp app = (TopApp) adapter.getItem(position);
        if (app == null) return;
        switch (view.getId()) {
            case R.id.tv_more:
                L.e("----------------more");
                break;
            case R.id.iv_direct:
                final DownloadUrl downloadUrl = new DownloadUrl();
                downloadUrl.setId(app.getId());
                downloadUrl.setName(app.getTitle());
                downloadUrl.setChannel(Constant.CHANNEL_DIRECT);
                for (int i = 0; i < app.getOther_download_url().size(); i++) {
                    if (Constant.CHANNEL_DIRECT.equals(app.getOther_download_url().get(i).getApp_market_name())) {
                        downloadUrl.setUrl(app.getOther_download_url().get(i).getDownload_url());
                        break;
                    }
                }
                DownloadControl.startDownload(downloadUrl, new DownloadProgressAdapter() {
                    @Override
                    public void onComplete() {
                        super.onComplete();
                        if (mListener != null)
                            mListener.onDownloadCompleted(downloadUrl.getName());
                    }
                });
                break;
            case R.id.iv_googleplay:
                break;
            case R.id.iv_wandoujia:
                break;
            case R.id.ibtn_control:
                boolean downloadable = !TextUtils.isEmpty(app.getDownload_url()) || !(app.getOther_download_url() == null || app.getOther_download_url().isEmpty());
                if (downloadable) {
                    app.setShow(!app.isShow());
                    for (int i = 0; i < adapter.getData().size(); i++) {
                        TopApp topApp = ((ItemAdapter) adapter).getData().get(i);
                        if (app.getId() == topApp.getId()) continue;
                        topApp.setShow(false);
                    }
                    app.setShow(!app.isShow());
                    notifyDataSetChanged();
                } else {
                    if (mListener == null) return;
                    mListener.onItemClick(app);
                }
                break;
        }

    }

    public interface OnCategoryAdapterClickListener {
        void onItemClick(TopApp app);

        void onDownloadCompleted(String name);

        void onMore(Category category);
    }

    public void setOnCategoryAdapterClickListener(OnCategoryAdapterClickListener listener) {
        mListener = listener;
        setOnItemChildClickListener(this);
    }

    public CategoryAdapter(@Nullable List<Category> data) {
        super(R.layout.rv_category_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        RecyclerView rvContent = helper.getView(R.id.rv_content);
        rvContent.setLayoutManager(null);
        rvContent.setLayoutManager(new UnScrollLinearLayoutManager(mContext));
        int itemDecorationCount = rvContent.getItemDecorationCount();
        if (itemDecorationCount <= 0)
            rvContent.addItemDecoration(new DividerLinearItemDecoration(mContext));
        ItemAdapter adapter = new ItemAdapter(item.getTop_apps(), 5);
        rvContent.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        if (!TextUtils.isEmpty(item.getIcon_url())) {
            Glide.with(mContext)
                    .load(item.getIcon_url())
                    .into((ImageView) helper.getView(R.id.iv_icon));
        }
        if ("每日最美".equals(item.getTitle())) {
            helper.setImageResource(R.id.iv_icon, R.mipmap.search_icon_daily);
        } else if ("发现应用".equals(item.getTitle())) {
            helper.setImageResource(R.id.iv_icon, R.mipmap.search_icon_discovery);
        }
        helper.setText(R.id.tv_title, item.getTitle());
        helper.addOnClickListener(R.id.tv_more);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TopApp app = (TopApp) adapter.getItem(position);
        if (app == null) return;
        if (mListener == null) return;
        mListener.onItemClick(app);
    }

    public static class ItemAdapter extends BaseQuickAdapter<TopApp, BaseViewHolder> {
        private int mMaxShow = -1;//最大显示条目

        ItemAdapter(@Nullable List<TopApp> data, int maxShow) {
            super(R.layout.rv_category_item_item);
            mMaxShow = maxShow;
            setNewData(data);
        }

        public ItemAdapter(@Nullable List<TopApp> data) {
            super(R.layout.rv_category_item_item);
            setNewData(data);
        }

        @Override
        public void setNewData(@Nullable List<TopApp> data) {
            //item条目控制显示最多5条
            if (data == null) return;
            if (mMaxShow == -1) {
                super.setNewData(data);
                return;
            }
            if (data.size() > mMaxShow) {
                List<TopApp> d = new ArrayList<>();
                for (int i = 0; i < mMaxShow; i++) {
                    d.add(data.get(i));
                }
                super.setNewData(d);
            } else {
                super.setNewData(data);
            }

        }

        @Override
        protected void convert(BaseViewHolder helper, TopApp item) {
            if (!TextUtils.isEmpty(item.getIcon_url())) {
                Glide.with(mContext)
                        .load(item.getIcon_url())
                        .into((ImageView) helper.getView(R.id.iv_icon));
            }
            boolean downloadable = !TextUtils.isEmpty(item.getDownload_url()) || !(item.getOther_download_url() == null || item.getOther_download_url().isEmpty());
            if (downloadable) {
                helper.setImageResource(R.id.ibtn_control, R.drawable.download_selector);
            } else {
                helper.setImageResource(R.id.ibtn_control, R.drawable.category_enter_topic_selector);
            }
            helper.addOnClickListener(R.id.ibtn_control);
            helper.addOnClickListener(R.id.iv_direct);
            helper.addOnClickListener(R.id.iv_googleplay);
            helper.addOnClickListener(R.id.iv_wandoujia);
            helper.setText(R.id.tv_title, item.getTitle());
            helper.setText(R.id.tv_sub_title, item.getSub_title());
            helper.getView(R.id.cl_download).setVisibility(item.isShow() ? View.VISIBLE : View.GONE);
            if (helper.getView(R.id.cl_download).getVisibility() == View.VISIBLE) {
                if (!TextUtils.isEmpty(item.getDownload_url())) {
                    helper.getView(R.id.iv_googleplay).setVisibility(View.VISIBLE);
                }
                if (item.getOther_download_url() != null && !item.getOther_download_url().isEmpty()) {
                    for (int i = 0; i < item.getOther_download_url().size(); i++) {
                        DownloadMethod downloadMethod = item.getOther_download_url().get(i);
                        if (Constant.CHANNEL_WANDOUJIA.equals(downloadMethod.getApp_market_name())) {
                            helper.getView(R.id.iv_wandoujia).setVisibility(View.VISIBLE);
                        } else if (Constant.CHANNEL_DIRECT.equals(downloadMethod.getApp_market_name())) {
                            helper.getView(R.id.iv_direct).setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
    }
}
