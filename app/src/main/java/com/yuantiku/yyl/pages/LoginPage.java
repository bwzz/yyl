package com.yuantiku.yyl.pages;

import android.view.View;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperActivityToast;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.SuperToast.Duration;
import com.yuantiku.yyl.R;
import com.yuantiku.yyl.helper.L;
import com.yuantiku.yyl.helper.UserHelper;
import com.yuantiku.yyl.helper.ZGYWikiHelper;
import com.yuantiku.yyl.util.OtherUtils;

import butterknife.InjectView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;

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
        OtherUtils.hideSoftKeyboard(getActivity(), password);
        L.i("login ", un);
        SuperActivityToast superActivityToast = new SuperActivityToast(getActivity(),
                SuperToast.Type.PROGRESS);
        superActivityToast.setText("登录中...");
        superActivityToast.show();
        ZGYWikiHelper.INSTANCE.login(un, pw)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> {
                            superActivityToast.dismiss();
                            UserHelper.INSTANCE.saveToken("Token " + success.get("token"));
                            pageManager.pop(LoginPage.this);
                            myObservable.notifyObservers(success);
                        },
                        failure -> {
                            L.e(failure);
                            superActivityToast.dismiss();
                            SuperToast.create(getActivity(), "登录失败", Duration.SHORT).show();
                        });
    }

    @Override
    public boolean interceptBackPressed() {
        L.i("interceptBackPressed");
        getActivity().finish();
        return true;
    }
}
