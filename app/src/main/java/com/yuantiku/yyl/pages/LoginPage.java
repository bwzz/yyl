package com.yuantiku.yyl.pages;

import android.view.View;
import android.widget.TextView;

import com.yuantiku.dbdata.Account;
import com.yuantiku.yyl.R;
import com.yuantiku.yyl.helper.LoginHelper;
import com.yuantiku.yyl.util.LogUtils;
import com.yuantiku.yyl.webadapter.WikiAdapter;

import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.functions.Action1;

/**
 * @author wanghb
 * @date 15/4/25.
 */
public class LoginPage extends BasePage {

    @InjectView(R.id.username)
    TextView username;

    @InjectView(R.id.password)
    TextView password;

    private Action1<Account> onNextAction;

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
        onNextAction = account -> LogUtils.e(account.getName());
        LoginHelper.helper.setOnNextAction(onNextAction);
        LoginHelper.helper.login(un, pw);
    }

    @Override
    public boolean interceptBackPressed() {
        LogUtils.i("interceptBackPressed");
        return true;
    }
}
