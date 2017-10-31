package com.leyifu.firenewss.app;

import android.app.Application;
import android.content.Context;

/**
 * @autoor hahaha
 * @TIME 2017/10/30 0030.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }


    public static Context getAppContext() {
        return context;
    }



//    /**
//     * 应用退出，结束所有的activity
//     */
    public static void exit() {

        System.exit(0);
    }
}
