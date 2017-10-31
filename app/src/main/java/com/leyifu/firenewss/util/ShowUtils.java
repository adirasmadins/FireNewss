package com.leyifu.firenewss.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @autoor hahaha
 * Created by hahaha on 2017/10/26 0026.
 */

public class ShowUtils {

    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int NOTHING = 6;
    private static int LEVEL = VERBOSE;
    private static Toast toast;


    public static void v(String TAG, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(TAG, "msg: " + msg);
        }
    }

    public static void d(String TAG, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(TAG, "msg: " + msg);
        }
    }

    public static void i(String TAG, String msg) {
        if (LEVEL <= INFO) {
            Log.i(TAG, "msg: " + msg);
        }
    }

    public static void w(String TAG, String msg) {
        if (LEVEL <= WARN) {
            Log.w(TAG, "msg: " + msg);
        }
    }

    public static void e(String TAG, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(TAG, "msg: " + msg);
        }
    }

    public static void toast(Context context, String msg) {
        if (toast != null) {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
