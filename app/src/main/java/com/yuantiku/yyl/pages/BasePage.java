package com.yuantiku.yyl.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuantiku.yyl.observe.MyObservable;
import com.yuantiku.yyl.observe.MyObserver;

import butterknife.ButterKnife;

/**
 * @author wanghb
 * @date 15/4/25.
 */
public class BasePage extends Fragment implements MyObserver {

    protected MyObservable myObservable = new MyObservable();

    public void addObserver(MyObserver myObserver) {
        myObservable.addObserver(myObserver);
    }

    protected PageManager pageManager;

    public void setPageManager(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @Override
    public void onDestroy() {
        myObservable.deleteObservers();
        super.onDestroy();
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View view = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.inject(this, view);
            setupView(view);
            return view;
        }
    }

    protected int getLayoutId() {
        return 0;
    }

    protected View setupView(View view) {
        return view;
    }

    public boolean interceptBackPressed() {
        return false;
    }

    @Override
    public void update(MyObservable myObservable, Object data) {

    }

    @Deprecated
    @Override
    public final void update(java.util.Observable observable, Object data) {
        update((MyObservable) observable, data);
    }
}
