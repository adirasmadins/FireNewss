package com.leyifu.firenewss.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hahaha on 2017/8/28 0028.
 */

public class ActivityCollectionUtil {

    static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
