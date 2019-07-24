package com.xuwuji.tuding.utillib.util;

import com.google.gson.Gson;
import com.xuwuji.tuding.utillib.AppConfig;

public class GsonUtil {
    private Gson gson;
    //    private Type type;
//    private Class<T> clazz;
    private static GsonUtil instance;


    private GsonUtil() {
        if (gson == null) {
            gson = new Gson();
        }
    }

    public static GsonUtil instance() {
        if (instance == null) {
            synchronized (GsonUtil.class) {
                if (instance == null) {
                    instance = new GsonUtil();
                }
            }
        }
        return instance;
    }


    public <T> T fromJson(String json, Class<T> cls) {
        try {
            T t = null;
            if (gson != null) {
                t = gson.fromJson(json, cls);
            }
            return t;
        } catch (Exception e) {
//            MobclickAgent.reportError(AppConfig.app, e.getMessage());
            return null;
        }
    }
}
