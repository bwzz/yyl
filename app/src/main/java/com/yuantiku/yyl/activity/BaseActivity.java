package com.yuantiku.yyl.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

import com.yuantiku.yyl.observe.Observable;
import com.yuantiku.yyl.observe.Observer;

/**
 * @author lirui
 * @date 15/4/13.
 */
public abstract class BaseActivity extends AppCompatActivity implements Observer {

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
