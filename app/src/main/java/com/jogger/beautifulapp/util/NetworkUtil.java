package com.jogger.beautifulapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Jogger on 2018/6/10.网络工具
 */

public class NetworkUtil {
    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) Util.getApp()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo current = cm.getActiveNetworkInfo();
        return current != null && (current.isAvailable());
    }
}
