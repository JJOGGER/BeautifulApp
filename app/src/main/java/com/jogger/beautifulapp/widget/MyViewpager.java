package com.jogger.beautifulapp.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Jogger on 2018/6/17.无切换效果的viewpager
 */

public class MyViewpager extends ViewPager {
    public MyViewpager(@NonNull Context context) {
        this(context, null);
    }

    public MyViewpager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }
}
