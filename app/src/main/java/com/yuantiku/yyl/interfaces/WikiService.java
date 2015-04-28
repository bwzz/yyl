package com.yuantiku.yyl.interfaces;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * @author lirui
 * @date 15/4/27.
 */
public interface WikiService {

    @FormUrlEncoded
    @POST("/FrontPage?action=login")
    void login(@Field("name") String username, @Field("password") String password,
            @Field("login") String login, Callback<String> res);

    @GET("/Livecast")
    void test(Callback<String> res);
}
