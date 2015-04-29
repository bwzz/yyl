package com.yuantiku.yyl;

import android.app.Application;

import com.yuantiku.yyl.util.CrashHandler;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

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
        application = this;
        CrashHandler.getInstance().init(this);
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
    }
}
