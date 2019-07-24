package com.xuwuji.tuding.utillib;

import android.app.Application;

public class AppConfig {
    private static Application app;

    public static void init(Application app) {
        AppConfig.app = app;
    }

    public static String token;

    public static Application getApp() {
        return app;
    }



    //测试
    public static final boolean isDebug = true;


    //正式
//    public static final boolean isDebug = false;
}
