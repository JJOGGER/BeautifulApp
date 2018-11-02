package com.jogger.beautifulapp.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.jogger.beautifulapp.BuildConfig;
import com.jogger.beautifulapp.db.DBManager;


/**
 * Created by jogger on 2018/5/25.控制中心
 */

public class Util {
    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    public static void init(@NonNull final Context context) {
        init((Application) context.getApplicationContext());
    }

    public static void init(@NonNull final Application app) {
        if (sApplication == null) {
            sApplication = app;
        }
        if (BuildConfig.DEBUG)
            L.plant(new L.DebugTree());
//        //初始化数据库
        DBManager.initDB(app);
//        PushManager.initPush();
    }

    public static Application getApp() {
        if (sApplication != null) return sApplication;
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object at = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(at);
            if (app == null) {
                throw new NullPointerException("u should init first");
            }
            init((Application) app);
            return sApplication;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new NullPointerException("u should init first");
    }

}
