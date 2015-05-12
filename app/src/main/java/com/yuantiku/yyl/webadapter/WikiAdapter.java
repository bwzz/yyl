package com.yuantiku.yyl.webadapter;

import com.google.gson.JsonObject;
import com.yuantiku.yyl.MyApplication;
import com.yuantiku.yyl.data.Result;
import com.yuantiku.yyl.data.ResultList;
import com.yuantiku.yyl.helper.UserHelper;

import java.util.List;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * @author lirui
 * @date 15/4/27.
 */
public class WikiAdapter {


    public static WikiService getService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://oa.zhenguanyu.com")
                .setLogLevel(LogLevel.BASIC)
//                .setClient(new OkClient(MySSLTrust.trustcert(MyApplication.getInstance())))
//                .setConverter(new MyGsonConvertor(new Gson()))
                .build();
        return restAdapter.create(WikiService.class);
    }

    public interface WikiService {

        @FormUrlEncoded
        @POST("/gettoken/")
        Observable<Map<String, String>> login(@Field("username") String username, @Field
                ("password") String password);

        @GET("/stafflist")
        Observable<ResultList> getMembers(@Header("Authorization") String token);

        @GET("/staffinfo")
        Observable<Result> getUserDetail(@Header("Authorization") String token, @Query("user")
                                         String ldap);
    }
}
