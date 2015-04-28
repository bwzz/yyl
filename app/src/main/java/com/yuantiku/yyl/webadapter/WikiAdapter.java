package com.yuantiku.yyl.webadapter;

import com.yuantiku.yyl.interfaces.WikiService;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

/**
 * @author lirui
 * @date 15/4/27.
 */
public class WikiAdapter {

    public static WikiService getService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://wiki.zhenguanyu.com")
                .setLogLevel(LogLevel.BASIC)
                .build();
        return restAdapter.create(WikiService.class);
    }
}
