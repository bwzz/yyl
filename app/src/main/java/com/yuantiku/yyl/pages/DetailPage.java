package com.yuantiku.yyl.pages;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yuantiku.dbdata.Account;
import com.yuantiku.yyl.R;

import java.util.Random;

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

    @InjectView(R.id.constellation)
    TextView constellation;

    @InjectView(R.id.constellationName)
    TextView constellationName;

    @InjectView(R.id.avatar)
    SimpleDraweeView avatar;

    private Account contact;

    @Override
    protected int getLayoutId() {
        return R.layout.page_detail;
    }

    @Override
    protected View setupView(View view) {
        contact = (Account) getArguments().get(Account.class.getName());
        name.setText(contact.getName());
        avatar.setImageURI(Uri.parse("res://drawable/" + R.drawable.at), this);
        phone.setText(contact.getPhone());
        email.setText(contact.getEmail());
        birthday.setText(contact.getBirth());
        constellation.setText(contact.getConstellation());

        // TODO
        constellationName.setText(contact.getConstellation());
        ShapeDrawable shape = new ShapeDrawable(new OvalShape());
        Random random = new Random();
        shape.getPaint().setColor(Color.argb(220, random.nextInt(255), random.nextInt(255),
                random.nextInt(255)));
        constellationName.setBackgroundDrawable(shape);
        avatar.setVisibility(View.INVISIBLE);

        return super.setupView(view);
    }

    @OnClick(R.id.phone)
    public void call(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel://" + contact.getPhone()));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // TODO
        }
    }
}
