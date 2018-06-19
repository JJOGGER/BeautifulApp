package com.jogger.beautifulapp.util;

public class SizeUtil {
    private SizeUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int dp2px(final float dpValue) {
        final float scale = Util.getApp().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(final float pxValue) {
        final float scale = Util.getApp().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getScreenWidth() {
        return Util.getApp().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Util.getApp().getResources().getDisplayMetrics().heightPixels;
    }
}
