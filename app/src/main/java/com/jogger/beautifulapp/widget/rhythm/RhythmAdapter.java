package com.jogger.beautifulapp.widget.rhythm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.entity.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 钢琴布局适配器
 */
public class RhythmAdapter extends BaseAdapter {

    /**
     * item的宽度
     */
    private float itemWidth;
    /**
     * 数据源
     */
    private List<AppInfo> mCardList;

    private LayoutInflater mInflater;
    private Context mContext;
    private RhythmLayout mRhythmLayout;

    public RhythmAdapter(Context context, RhythmLayout rhythmLayout, List<AppInfo> cardList) {
        this.mContext = context;
        this.mRhythmLayout = rhythmLayout;
        this.mCardList = new ArrayList<>();
        this.mCardList.addAll(cardList);
        if (context != null)
            this.mInflater = LayoutInflater.from(context);
    }

    public List<AppInfo> getCardList() {
        return this.mCardList;
    }

    public void addCardList(List<AppInfo> cardList) {
        mCardList.addAll(cardList);
    }

    public int getCount() {
        return this.mCardList.size();
    }

    public Object getItem(int position) {
        return this.mCardList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

//    public long getItemId(int paramInt) {
//        return (this.mCardList.get(paramInt)).getUid();
//    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") RelativeLayout relativeLayout = (RelativeLayout) mInflater.inflate(R.layout.adapter_rhythm_icon, parent,false);
        //设置item布局的大小以及Y轴的位置
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams((int) itemWidth, mContext.getResources().getDimensionPixelSize(R.dimen.rhythm_item_height)));
        relativeLayout.setTranslationY(itemWidth);

        //设置第二层RelativeLayout布局的宽和高
        RelativeLayout childRelativeLayout = (RelativeLayout) relativeLayout.getChildAt(0);
        int relativeLayoutWidth = (int) itemWidth - 2 * mContext.getResources().getDimensionPixelSize(R.dimen.rhythm_icon_margin);
        childRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(relativeLayoutWidth, mContext.getResources().getDimensionPixelSize(R.dimen.rhythm_item_height) - 2 * mContext.getResources().getDimensionPixelSize(R.dimen.rhythm_icon_margin)));

        final ImageView imageIcon = (ImageView) relativeLayout.findViewById(R.id.riv_icon);
        //计算ImageView的大小
        final int iconSize = (relativeLayoutWidth - 2 * mContext.getResources().getDimensionPixelSize(R.dimen.rhythm_icon_margin));
        //设置背景图片
        Glide.with(mContext)
                .load(mCardList.get(position).getIcon_image())
                .error(R.mipmap.ic_launcher)
//                .config(Bitmap.Config.RGB_565)
//                .transform(ImgUtil.getTransformation(iconSize,iconSize))
                .into(imageIcon);
        ViewGroup.MarginLayoutParams iconParams = (ViewGroup.MarginLayoutParams) imageIcon.getLayoutParams();
        iconParams.leftMargin=2;
        imageIcon.setLayoutParams(iconParams);

        return relativeLayout;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.mRhythmLayout.invalidateData();
    }

    public void setCardList(List<AppInfo> paramList) {
        this.mCardList = paramList;
    }

    /**
     * 设置每个item的宽度
     */
    public void setItemWidth(float width) {
        this.itemWidth = width;
    }
}