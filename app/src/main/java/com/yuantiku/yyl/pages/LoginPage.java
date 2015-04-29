package com.yuantiku.yyl.pages;

import android.view.View;
import android.widget.TextView;

import com.yuantiku.yyl.R;
import com.yuantiku.yyl.helper.L;
import com.yuantiku.yyl.util.LogUtils;
import com.yuantiku.yyl.webadapter.WikiAdapter;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author wanghb
 * @date 15/4/25.
 */
public class LoginPage extends BasePage {

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
        WikiAdapter.getService()
                .login(un, pw, "Login")
                .subscribe(success -> {
                    pageManager.pop(LoginPage.this);
                    myObservable.notifyObservers(success);
                },
                        failure -> L.e(failure.getMessage()));
    }

    @Override
    public boolean interceptBackPressed() {
        LogUtils.i("interceptBackPressed");
        return true;
    }
}
