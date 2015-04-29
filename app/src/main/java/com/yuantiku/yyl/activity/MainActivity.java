package com.yuantiku.yyl.activity;

import android.os.Bundle;

import com.yuantiku.yyl.R;
import com.yuantiku.yyl.helper.LoginHelper;
import com.yuantiku.yyl.observe.Observable;
import com.yuantiku.yyl.observe.Observer;
import com.yuantiku.yyl.pages.BasePage;
import com.yuantiku.yyl.pages.ContactsPage;
import com.yuantiku.yyl.pages.LoginPage;
import com.yuantiku.yyl.pages.PageManager;

public class MainActivity extends BaseActivity implements Observer {

    private PageManager pageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageManager = new PageManager(getSupportFragmentManager(), R.id.container);
        BasePage loginPage = new LoginPage();
        loginPage.addObserver(this);
        pageManager.push(loginPage);
        LoginHelper.helper.login("lirui", "");
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
    public void update(Observable observable, Object data) {
        pageManager.push(new ContactsPage(), null, false);
    }

}
