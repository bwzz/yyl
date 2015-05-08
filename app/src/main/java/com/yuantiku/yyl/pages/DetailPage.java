package com.yuantiku.yyl.pages;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yuantiku.dbdata.Account;
import com.yuantiku.yyl.R;

import java.util.Random;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author wanghb
 * @date 15/4/29.
 */
public class DetailPage extends FragmentPage {
    @InjectView(R.id.name)
    TextView name;

    @InjectView(R.id.phone)
    TextView phone;

    @InjectView(R.id.email)
    TextView email;

    @InjectView(R.id.birthday)
    TextView birthday;

    @InjectView(R.id.googleAccount)
    TextView googleAccount;

    @InjectView(R.id.constellationName)
    TextView constellationName;

    private Account contact;

    @Override
    protected int getLayoutId() {
        return R.layout.page_detail;
    }

    @Override
    protected View setupView(View view) {
        contact = (Account) getArguments().get(Account.class.getName());
        name.setText(contact.getName());
        phone.setText(contact.getPhone());
        email.setText(contact.getEmail());
        birthday.setText(contact.getBirth());
        googleAccount.setText(contact.getGoogleAccount());

        // TODO
        constellationName.setText(contact.getConstellation());
        ShapeDrawable shape = new ShapeDrawable(new OvalShape());
        Random random = new Random();
        shape.getPaint().setColor(Color.argb(220, random.nextInt(255), random.nextInt(255),
                random.nextInt(255)));
        constellationName.setBackgroundDrawable(shape);

        return super.setupView(view);
    }

    @OnClick({R.id.phone, R.id.email, R.id.googleAccount})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phone:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel://" + contact.getPhone()));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // TODO
                }
                break;
            case R.id.email:
                emailTo(contact.getEmail());
                break;
            case R.id.googleAccount:
                emailTo(contact.getGoogleAccount());
                break;

        }
    }

    private void emailTo(String email) {
        if (TextUtils.isEmpty(email)) {
            return;
        }
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:" + email));
        data.putExtra(Intent.EXTRA_SUBJECT, "");
        data.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(data);
    }
}
