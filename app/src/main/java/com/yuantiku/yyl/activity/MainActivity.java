package com.yuantiku.yyl.activity;

import android.os.Bundle;

import com.yuantiku.yyl.R;
import com.yuantiku.yyl.observe.MyObservable;
import com.yuantiku.yyl.observe.MyObserver;
import com.yuantiku.yyl.pages.BasePage;
import com.yuantiku.yyl.pages.ContactsPage;
import com.yuantiku.yyl.pages.LoginPage;
import com.yuantiku.yyl.pages.PageManager;

public class MainActivity extends BaseActivity implements MyObserver {

    private PageManager pageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageManager = new PageManager(getSupportFragmentManager(), R.id.container);
        BasePage loginPage = new LoginPage();
        loginPage.addObserver(this);
        pageManager.push(loginPage);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (!pageManager.interceptBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void update(MyObservable myObservable, Object data) {
        pageManager.push(new ContactsPage(), null, false);
    }

}
