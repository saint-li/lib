package com.saint.mylib;

import android.app.Application;
import com.xuwuji.tuding.utillib.AppConfig;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.init(this);
    }
}
