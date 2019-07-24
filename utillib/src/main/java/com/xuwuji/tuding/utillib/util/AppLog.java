package com.xuwuji.tuding.utillib.util;

import android.text.TextUtils;
import android.util.Log;
import com.xuwuji.tuding.utillib.AppConfig;

/**
 * `Author: Administrator
 * Time: 2018/6/6 14:47
 * ReadMe:
 */
public class AppLog {
    private static String TAG = "APP_LOG";

    public static void d(String TAG, String str) {
        if (AppConfig.isDebug)
            Log.d(TAG, str);
    }

    public static void v(String TAG, String str) {
        if (AppConfig.isDebug)
            Log.v(TAG, str);
    }

    public static void e(String TAG, String str) {
        if (AppConfig.isDebug) {
            if (!TextUtils.isEmpty(str)) {
                Log.e(TAG, CodeUtil.unicodeToUTF_8(str));
            } else {
                Log.e(TAG, "日志为null");
            }
        }
    }

    public static void i(String TAG, String str) {
        if (AppConfig.isDebug)
            Log.i(TAG, str);
    }

    public static void i(String str) {
        if (AppConfig.isDebug)
            Log.i(TAG, str);
    }

    public static void d(String str) {
        if (AppConfig.isDebug)
            Log.d(TAG, str);
    }

    public static void v(String str) {
        if (AppConfig.isDebug)
            Log.v(TAG, str);
    }

    public static void e(String str) {
        if (AppConfig.isDebug) {
            if (!TextUtils.isEmpty(str)) {
                Log.e(TAG, CodeUtil.unicodeToUTF_8(str));
            } else {
                Log.e(TAG, "日志为null");
            }
        }
    }


}