package com.yuantiku.yyl.pages;

import android.view.View;
import android.widget.TextView;

import com.yuantiku.dbdata.Account;
import com.yuantiku.yyl.R;

import butterknife.InjectView;

/**
 * @author wanghb
 * @date 15/4/29.
 */
public class DetailPage extends BasePage {
    @InjectView(R.id.name)
    TextView name;

    @Override
    protected int getLayoutId() {
        return R.layout.page_detail;
    }

    @Override
    protected View setupView(View view) {
        Account contact = (Account) getArguments().get(Account.class.getName());
        name.setText(contact.getName());
        return super.setupView(view);
    }
}
