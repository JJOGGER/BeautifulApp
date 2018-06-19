package com.jogger.beautifulapp.util;

import android.app.Activity;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jogger.beautifulapp.R;


public class StatusUtil {
    public static void immersive(Activity activity, int immersiveColor) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(immersiveColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        View view = activity.findViewById(R.id.immersive);
        if (view != null) {
            int statusHeight =getStatusHeight();
            if (statusHeight != -1) {
                view.setPadding(0, statusHeight, 0, 0);
            }
        }
    }

    public static int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        if (Util.getApp().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(
                    tv.data, Util.getApp().getResources().getDisplayMetrics()
            );
        }
        return 0;
    }

    public static void setActivityFullScreen(Activity activity) {
        if (activity != null) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                    .FLAG_FULLSCREEN);
        }
    }

    public void setActivityUnFullScreen(Activity activity) {
        if (activity != null) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams
                    .FLAG_FULLSCREEN);
        }
    }

    public static int getStatusHeight() {
        int resid =Util.getApp().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resid > 0) {
            return Util.getApp().getResources().getDimensionPixelSize(resid);//通过资源id获得资源所对应的值
        }
        return -1;
    }
}
