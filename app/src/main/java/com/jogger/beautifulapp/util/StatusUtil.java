package com.jogger.beautifulapp.util;

import android.app.Activity;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by it on 2017/2/21.
 */

public class StatusUtil {
    public static void immersive(Activity activity, int immersiveColor) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(immersiveColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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
}
