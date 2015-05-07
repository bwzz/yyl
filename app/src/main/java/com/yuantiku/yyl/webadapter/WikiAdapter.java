package com.yuantiku.yyl.webadapter;

import com.google.gson.Gson;
import com.yuantiku.yyl.MyApplication;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * @author lirui
 * @date 15/4/27.
 */
public class WikiAdapter {

    public static WikiService getService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://wiki.zhenguanyu.com")
                .setLogLevel(LogLevel.BASIC)
                .setClient(new OkClient(MySSLTrust.trustcert(MyApplication.getInstance())))
                .setConverter(new MyGsonConvertor(new Gson()))
                .build();
        return restAdapter.create(WikiService.class);
    }

    public interface WikiService {

        @FormUrlEncoded
        @POST("/FrontPage?action=login")
        Observable<Response> login(@Field("name") String username, @Field("password") String password,
                                   @Field("login") String login);

        @GET("/TeamMembers")
        Observable<Response> getMembers();
    }
}
