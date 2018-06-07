package com.jogger.beautifulapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Created by it on 2017/2/22.
 * 土司工具
 */

public class T {
    private static Toast toast;
    private static Toast customToast;

    @SuppressLint("ShowToast")
    public static void show(String str) {
        if (toast == null) {
            toast = Toast.makeText(Util.getApp(), str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    @SuppressLint("ShowToast")
    public static void show(int str) {
        if (toast == null) {
            toast = Toast.makeText(Util.getApp(),
                    str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    @SuppressLint("ShowToast")
    public static void showFullToast(Context context, int layoutId) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(layoutId, null);
        if (customToast == null) {
            customToast = Toast.makeText(context.getApplicationContext(),
                    null, Toast.LENGTH_SHORT);
        }
        customToast.setGravity(Gravity.FILL, 0, 0);
        customToast.setView(view);
        customToast.show();
    }
}
