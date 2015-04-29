package com.yuantiku.yyl.webadapter;

import com.google.gson.Gson;
import com.yuantiku.yyl.MyApplication;
import com.yuantiku.yyl.interfaces.WikiService;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.OkClient;

/**
 * @author lirui
 * @date 15/4/27.
 */
public class WikiAdapter {

    public static WikiService getService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://wiki.zhenguanyu.com")
                .setLogLevel(LogLevel.FULL)
                .setClient(new OkClient(MySSLTrust.trustcert(MyApplication.getInstance())))
                .setConverter(new MyGsonConvertor(new Gson()))
                .build();
        return restAdapter.create(WikiService.class);
    }
}
