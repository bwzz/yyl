package com.yuantiku.yyl.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.yuantiku.yyl.R;
import com.yuantiku.yyl.pages.ContactsPage;
import com.yuantiku.yyl.pages.PageManager;
import com.yuantiku.yyl.util.PersistentCookieStore;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                PersistentCookieStore.getInstance(this).removeAll();
                pageManager.popAll();
                pageManager.push(new ContactsPage(), null, false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
