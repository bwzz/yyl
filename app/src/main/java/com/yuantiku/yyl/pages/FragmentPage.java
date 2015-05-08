package com.yuantiku.yyl.pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuantiku.yyl.helper.L;
import com.yuantiku.yyl.observe.MyObservable;
import com.yuantiku.yyl.observe.MyObserver;
import com.yuantiku.yyl.pages.interfaces.Page;
import com.yuantiku.yyl.pages.interfaces.PageManager;

import butterknife.ButterKnife;

/**
 * @author wanghb
 * @date 15/4/25.
 */
public abstract class FragmentPage extends Fragment implements MyObserver, Page {

    protected MyObservable myObservable = new MyObservable();

    public void addObserver(MyObserver myObserver) {
        myObservable.addObserver(myObserver);
    }

    protected PageManager pageManager;

    @Override
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
            view.setClickable(true);
            setupView(view);
            return view;
        }
    }

    protected abstract int getLayoutId();

    protected View setupView(View view) {
        return view;
    }

    public boolean interceptBackPressed() {
        pageManager.pop(this);
        return true;
    }

    @Override
    public void update(MyObservable myObservable, Object data) {

    }

    @Deprecated
    @Override
    public final void update(java.util.Observable observable, Object data) {
        update((MyObservable) observable, data);
    }

    @Override
    public void prePush() {
        L.i("prePush");
    }

    @Override
    public void postPush() {
        L.i("postPush");
    }

    @Override
    public void prePop() {
        L.i("prePop");
    }

    @Override
    public void postPop() {
        L.i("postPop");
    }
}
