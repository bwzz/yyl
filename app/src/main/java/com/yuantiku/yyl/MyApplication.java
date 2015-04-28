package com.yuantiku.yyl;

import android.app.Application;

import com.yuantiku.yyl.util.CrashHandler;

/**
 * @author lirui
 * @date 15/4/27.
 */
public class MyApplication extends Application {
    private static MyApplication application;

    public static MyApplication getInstance(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }
}
