package com.yuantiku.yyl.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.yuantiku.yyl.R;
import com.yuantiku.yyl.helper.AccountDBHelper;
import com.yuantiku.yyl.helper.UpdateHelper;
import com.yuantiku.yyl.pages.ContactsPage;
import com.yuantiku.yyl.pages.FragmentPageManager;
import com.yuantiku.yyl.pages.interfaces.PageManager;
import com.yuantiku.yyl.util.KeyboardUtils;
import com.yuantiku.yyl.util.PersistentCookieStore;

import butterknife.InjectView;
import rx.Observable;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private PageManager pageManager;
    private EditText searchText;
    private View searchWrapper;
    private ContactsPage contactsPage;

    private final static String CONTACTS = "contacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        pageManager = new FragmentPageManager(getSupportFragmentManager(), R.id.container);
        contactsPage = new ContactsPage();
        pageManager.push(contactsPage, CONTACTS);
        UpdateHelper.helper.checkUpdate(this);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getLayoutInflater().inflate(R.layout.view_toolbar_search, toolbar);
        getLayoutInflater().inflate(R.layout.view_search_text, toolbar);
        searchText = (EditText) findViewById(R.id.search_text);
        searchWrapper = findViewById(R.id.search_wrapper);
        ImageView searchIcon = (ImageView) findViewById(R.id.search);
        View backIcon = findViewById(R.id.back);
        searchIcon.setOnClickListener(event -> trySearch());
        backIcon.setOnClickListener(event -> stopSearch());
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search();
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (!pageManager.interceptBackPressed()) {
            if(searchWrapper.getVisibility() == View.VISIBLE) {
                stopSearch();
            } else {
                super.onBackPressed();
            }
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

    private void trySearch() {
        if (searchWrapper.getVisibility() == View.VISIBLE) {
            search();
        } else {
            setSearchable(true);
        }
    }

    private void search() {
        String text = searchText.getText().toString();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        Observable.just(AccountDBHelper.helper.query(text))
                .subscribe(contactsPage::updateData);
    }

    private void stopSearch() {
        setSearchable(false);
        contactsPage.updateData(AccountDBHelper.helper.getAccounts());
    }

    private void setSearchable(boolean show) {
        if (show) {
            searchWrapper.setVisibility(View.VISIBLE);
            KeyboardUtils.showSoftKeyBoard(this, searchText);
            searchText.requestFocus();
        } else {
            searchWrapper.setVisibility(View.GONE);
            KeyboardUtils.hideSoftKeyboard(this, searchText);
        }
    }
}
