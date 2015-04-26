package com.yuantiku.yyl.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.yuantiku.yyl.util.LogUtils;

import butterknife.ButterKnife;

/**
 * @author lirui
 * @date 15/4/13.
 */
public abstract class BaseActivity extends FragmentActivity {

    public static LogUtils L;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.inject(this);
    }

    protected abstract int getLayoutResId();
}
