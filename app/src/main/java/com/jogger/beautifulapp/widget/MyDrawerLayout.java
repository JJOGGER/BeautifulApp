package com.jogger.beautifulapp.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jogger.beautifulapp.util.L;


/**
 * Created by Jogger on 2018/6/5.
 * 抽屉
 */

public class MyDrawerLayout extends DrawerLayout {

    private static final String TAG = "MyDrawerLayout";

    public MyDrawerLayout(@NonNull Context context) {
        this(context, null);
        L.e("------MyDrawerLayout1");
    }

    public MyDrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        L.e("------MyDrawerLayout2");
        addDrawerListener(new SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                Log.d(TAG, "----offset:" + slideOffset);
                for (int i = 0; i < getChildCount(); i++) {
                    View childAt = getChildAt(i);
                    childAt.setScaleX(1);
                    childAt.setScaleX(1);
                }
            }
        });
    }

    public MyDrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        L.e("------MyDrawerLayou3t");
        addDrawerListener(new SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                Log.d(TAG, "----offset:" + slideOffset);
                for (int i = 0; i < getChildCount(); i++) {
                    View childAt = getChildAt(i);
                    childAt.setScaleX(1);
                    childAt.setScaleX(1);
                }
            }
        });
    }


}
