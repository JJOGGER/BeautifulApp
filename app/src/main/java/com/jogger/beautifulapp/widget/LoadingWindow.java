package com.jogger.beautifulapp.widget;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.util.SizeUtil;

/**
 * Created by jogger on 2018/10/10.
 */
public class LoadingWindow {

    private PopupWindow mPopupWindow;
    private AnimationDrawable mLoadingAnim;

    public void show(Activity activity) {
        if (activity.isDestroyed() || activity.isFinishing() || activity.getFragmentManager() == null)
            return;
        if (mPopupWindow == null) {
            View view = LayoutInflater.from(activity).inflate(R.layout.window_loading_layout, null);
            mPopupWindow = new PopupWindow(view, SizeUtil.getScreenWidth(), SizeUtil.getScreenHeight());
            mPopupWindow.setTouchable(true);
            mPopupWindow.setOutsideTouchable(false);
            ImageView ivLoading = view.findViewById(R.id.iv_loading);
            ivLoading.setBackgroundResource(R.drawable.loading_anim);
            mLoadingAnim = (AnimationDrawable) ivLoading.getBackground();
        }
        if (mLoadingAnim != null && !mLoadingAnim.isRunning())
            mLoadingAnim.start();
        try {
            mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        } catch (Exception ignore) {
            mPopupWindow = null;
        }
    }

    public void dismiss() {
        if (mLoadingAnim != null && mLoadingAnim.isRunning()) {
            mLoadingAnim.stop();
        }
        if (mPopupWindow != null && mPopupWindow.isShowing())
            mPopupWindow.dismiss();
    }
}
