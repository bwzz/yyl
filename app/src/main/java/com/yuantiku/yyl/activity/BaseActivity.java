package com.yuantiku.yyl.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.yuantiku.yyl.observe.Observable;
import com.yuantiku.yyl.observe.Observer;

import butterknife.ButterKnife;

/**
 * @author lirui
 * @date 15/4/13.
 */
public abstract class BaseActivity extends ActionBarActivity implements Observer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.inject(this);
    }

    protected int getLayoutResId() {
        return 0;
    }

    @Override
    public final void update(java.util.Observable observable, Object data) {
        update((Observable) observable, data);
    }
}
