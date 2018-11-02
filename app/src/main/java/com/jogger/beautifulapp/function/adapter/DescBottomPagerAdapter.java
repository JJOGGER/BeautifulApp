package com.jogger.beautifulapp.function.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.util.L;
import com.jogger.beautifulapp.util.SizeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jogger on 2018/10/27.
 */
public class DescBottomPagerAdapter extends PagerAdapter implements View.OnClickListener {
    private ViewPager mViewPager;
    private List<View> mViews;

    @SuppressLint("InflateParams")
    public DescBottomPagerAdapter(Context context, ViewPager viewPager) {
        mViews = new ArrayList<>();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v;
        v = layoutInflater.inflate(R.layout.vp_desc_bottom_item1, null);
        v.findViewById(R.id.iv_flower).setOnClickListener(this);
        v.findViewById(R.id.iv_leaf).setOnClickListener(this);
        v.findViewById(R.id.fl_download).setOnClickListener(this);
        v.findViewById(R.id.ibtn_drag).setOnClickListener(this);
        mViews.add(v);
        v = layoutInflater.inflate(R.layout.vp_desc_bottom_item2, null);
        mViews.add(v);
        mViewPager = viewPager;
    }

    @Override
    public float getPageWidth(int position) {
        if (position == 0) {
            return 1;
        } else {
            View ibtnDrag = mViews.get(0).findViewById(R.id.ibtn_drag);
            if (ibtnDrag == null)
                return 1;
            else {
                ibtnDrag.measure(0, 0);
                float size = (float) ibtnDrag.getMeasuredWidth() / (float) SizeUtil.getScreenWidth();
                return 1 - size;
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (mListener == null) return;
        switch (view.getId()) {
            case R.id.iv_flower:
                mListener.onFlower();
                break;
            case R.id.iv_leaf:
                mListener.onLeaf();
                break;
            case R.id.fl_download:
                mListener.onDownload();
                break;
            case R.id.ibtn_drag:
                mViewPager.setCurrentItem(1 - mViewPager.getCurrentItem());
                break;
        }
    }

    public void setDownloadEnable(boolean enable) {
        for (int i = 0; i < mViews.size(); i++) {
            FrameLayout flDownload = mViews.get(i).findViewById(R.id.fl_download);
            if (flDownload == null) continue;
            flDownload.setEnabled(enable);
            break;
        }
    }

    public interface OnDescBottomClickListener {
        void onFlower();

        void onLeaf();

        void onDownload();
    }

    private OnDescBottomClickListener mListener;

    public void setOnDescBottomClickListener(OnDescBottomClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @SuppressWarnings("all")
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        mViews.remove(object);
        L.e("---------destroyItem");
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
