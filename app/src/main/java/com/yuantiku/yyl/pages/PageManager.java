package com.yuantiku.yyl.pages;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @author wanghb
 * @date 15/4/28.
 */
public class PageManager {
    private FragmentManager fragmentManager;

    private int containerViewId;

    public PageManager(FragmentManager fragmentManager, int containerViewId) {
        this.fragmentManager = fragmentManager;
        this.containerViewId = containerViewId;
    }

    public void push(BasePage page) {
        push(page, null);
    }

    public void push(BasePage page, String tag) {
        push(page, tag, true);
    }

    public void push(BasePage page, String tag, boolean addToBackStack) {
        page.setPageManager(this);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, page, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    public boolean interceptBackPressed() {
        BasePage page = (BasePage) fragmentManager.findFragmentById(containerViewId);
        return page.interceptBackPressed();
    }

    public void pop(BasePage page) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.remove(page);
        fragmentTransaction.commit();
    }
}
