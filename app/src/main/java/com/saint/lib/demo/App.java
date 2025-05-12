package com.saint.lib.demo;

import android.app.Application;

//import com.saint.lib.util.AppUtil;
//import com.saint.lib.util.UtilConfig;

/**
 * @author Saint  2020/12/18.
 * DESC：
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        if (AppUtil.isMainProcess(this)) {
//            UtilConfig.init(this,true);
//        }
    }
}
