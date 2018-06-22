package com.jogger.beautifulapp.base.recyclerview.refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.jogger.beautifulapp.R;

/**
 * Created by Jogger on 2018/6/22.
 */

public class DefaultRefreshCreator extends RefreshViewCreator {
    // 加载数据的ImageView
    private View mRefreshIv;
    private RotateAnimation mRotateAnimation;

    @Override
    public View getRefreshView(Context context, ViewGroup parent) {
        View refreshView = LayoutInflater.from(context).inflate(R.layout.refresh_recyclerview,
                parent, false);
        mRefreshIv = refreshView.findViewById(R.id.iv_refresh);
        return refreshView;
    }

    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        float rotate = ((float) currentDragHeight) / refreshViewHeight;
        // 不断下拉的过程中不断的旋转图片
        mRefreshIv.setRotation(rotate * 180);
    }

    @Override
    public void onRefreshing() {
        // 刷新的时候不断旋转
        if (mRotateAnimation == null) {
            mRotateAnimation = new RotateAnimation(0, 720,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            mRotateAnimation.setRepeatCount(-1);
            mRotateAnimation.setDuration(1000);
        }
        mRefreshIv.startAnimation(mRotateAnimation);
    }

    @Override
    public void onStopRefresh() {
        // 停止加载的时候清除动画
        mRefreshIv.setRotation(0);
        mRefreshIv.clearAnimation();
    }
}
