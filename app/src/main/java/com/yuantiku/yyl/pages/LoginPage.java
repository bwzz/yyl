package com.yuantiku.yyl.pages;

import android.view.View;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;

import com.yuantiku.yyl.R;
import com.yuantiku.yyl.helper.L;
import com.yuantiku.yyl.helper.ZGYWikiHelper;

/**
 * @author wanghb
 * @date 15/4/25.
 */
public class LoginPage extends FragmentPage {

    @InjectView(R.id.username)
    TextView username;

    @InjectView(R.id.password)
    TextView password;

    @Override
    protected int getLayoutId() {
        return R.layout.page_login;
    }

    @Override
    protected View setupView(View view) {
        return super.setupView(view);
    }

    @OnClick(R.id.submit)
    public void login(View view) {
        String un = username.getText().toString();
        String pw = password.getText().toString();
        ZGYWikiHelper.helper.login(un, pw)
                .subscribe(success -> {
                    pageManager.pop(LoginPage.this);
                    myObservable.notifyObservers(success);
                },
                        failure -> L.e(failure));
    }

    @Override
    public boolean interceptBackPressed() {
        L.i("interceptBackPressed");
        return true;
    }
}
