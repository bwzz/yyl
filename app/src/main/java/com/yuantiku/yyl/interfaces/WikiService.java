package com.yuantiku.yyl.interfaces;


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
public interface WikiService {

    @FormUrlEncoded
    @POST("/FrontPage?action=login")
    Observable<Response> login(@Field("name") String username, @Field("password") String password,
            @Field("login") String login);

    @GET("/TeamMembers")
    Observable<Response> getMembers();
}
