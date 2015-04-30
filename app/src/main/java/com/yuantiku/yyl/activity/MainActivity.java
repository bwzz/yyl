package com.yuantiku.yyl.activity;

import android.os.Bundle;

import com.yuantiku.yyl.R;
import com.yuantiku.yyl.pages.ContactsPage;
import com.yuantiku.yyl.pages.PageManager;

public class MainActivity extends BaseActivity {

    private PageManager pageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageManager = new PageManager(getSupportFragmentManager(), R.id.container);
        pageManager.push(new ContactsPage(), null, false);
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
}
