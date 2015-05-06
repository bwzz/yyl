package com.yuantiku.yyl.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.InjectView;

import com.yuantiku.yyl.R;
import com.yuantiku.yyl.helper.AccountDBHelper;
import com.yuantiku.yyl.pages.ContactsPage;
import com.yuantiku.yyl.pages.FragmentPageManager;
import com.yuantiku.yyl.pages.interfaces.PageManager;
import com.yuantiku.yyl.util.PersistentCookieStore;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    private PageManager pageManager;

    private final static String CONTACTS = "contacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        pageManager = new FragmentPageManager(getSupportFragmentManager(), R.id.container);
        pageManager.push(new ContactsPage(), CONTACTS);
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
        getMenuInflater().inflate(R.menu.menu_main, toolbar.getMenu());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                AccountDBHelper.helper.clear();
                PersistentCookieStore.getInstance(this).removeAll();
                pageManager.resetWithStartPage(new ContactsPage(), CONTACTS);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
