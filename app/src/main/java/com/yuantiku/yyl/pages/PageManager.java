package com.yuantiku.yyl.pages;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.yuantiku.yyl.helper.L;

/**
 * @author wanghb
 * @date 15/4/28.
 */
public class PageManager {
    private FragmentManager fragmentManager;

    private int containerViewId;

    private ViewGroup containerView;

    public PageManager(FragmentManager fragmentManager, ViewGroup containerView) {
        this.fragmentManager = fragmentManager;
        this.containerViewId = containerView.getId();
        this.containerView = containerView;
    }

    public void push(BasePage page, String tag) {
        L.i("push page " + tag + " : " + page.getClass().getName());
        page.setPageManager(this);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, page, tag);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    public boolean interceptBackPressed() {
        BasePage page = (BasePage) fragmentManager.findFragmentById(containerViewId);
        return page.interceptBackPressed();
    }

    public void pop(BasePage page) {
        L.i("pop page " + " : " + page.getClass().getName());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.remove(page);
        fragmentTransaction.commit();
    }

    public void resetWithStartPage(BasePage page, String tag) {
        L.i("reset with start page " + tag + ":" + page.getClass().getName());
        page.setPageManager(this);
        containerView.removeAllViews();
        fragmentManager.beginTransaction()
                .replace(containerViewId, page, tag)
                .commit();
    }
}
