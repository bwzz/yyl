package com.yuantiku.yyl.activity;

import android.os.Bundle;

import com.yuantiku.yyl.R;
import com.yuantiku.yyl.webadapter.WikiAdapter;
import com.yuantiku.yyl.interfaces.WikiService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author lirui
 * @date 15/4/26.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WikiService wikiService = WikiAdapter.getService();
        wikiService.login("lirui", "", "Login", new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                L.i("success");
            }

            @Override
            public void failure(RetrofitError error) {
                L.e(error.getMessage());
            }
        });

        wikiService.test(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                L.i(response.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                L.e(error.getMessage());
            }
        });
    }
}
