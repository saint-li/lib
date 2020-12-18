package com.saint.lib.util;

import android.app.Application;

public class UtilConfig {
    private static Application app;
    private static boolean isDebug = false;


    public static void init(Application app, boolean debug) {
        if (app == null) {
            throw new NullPointerException("初始化Util的application不可为null！");
        }
        if (!AppUtil.isMainProcess(app)) return;
        UtilConfig.app = app;
        isDebug = debug;

    }


    public static Application getApp() {
        return app;
    }

    public static void setIsDebug(boolean isDebug) {
        UtilConfig.isDebug = isDebug;
    }

    public static boolean isDebug() {
        return isDebug;
    }

}
