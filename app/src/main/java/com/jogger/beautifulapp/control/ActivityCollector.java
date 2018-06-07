package com.jogger.beautifulapp.control;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by it on 2017/3/6.
 * 窗体管理工具
 */

public class ActivityCollector {
    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity :
                activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void finishActivity(Class cls) {
        for (Activity activity :
                activities) {
            if (activity.getClass() == cls) {
                activity.finish();
                break;
            }
        }
    }

}
