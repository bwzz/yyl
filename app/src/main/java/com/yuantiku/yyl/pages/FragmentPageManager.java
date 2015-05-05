package com.yuantiku.yyl.pages;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.yuantiku.yyl.pages.interfaces.Page;

/**
 * @author wanghb
 * @date 15/4/28.
 */
public class FragmentPageManager extends AbstractPageManager {
    private FragmentManager fragmentManager;

    private int containerViewId;

    public FragmentPageManager(FragmentManager fragmentManager, int containerViewId) {
        this.fragmentManager = fragmentManager;
        this.containerViewId = containerViewId;
    }

    @Override
    protected void pushPage(Page page, String tag) {
        FragmentPage fragmentPage = (FragmentPage) page;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, fragmentPage, tag);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    public boolean interceptBackPressed() {
        FragmentPage page = (FragmentPage) fragmentManager.findFragmentById(containerViewId);
        return page.interceptBackPressed();
    }

    @Override
    protected void popPage(Page page) {
        FragmentPage fragmentPage = (FragmentPage) page;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.remove(fragmentPage);
        fragmentTransaction.commit();
    }

}
