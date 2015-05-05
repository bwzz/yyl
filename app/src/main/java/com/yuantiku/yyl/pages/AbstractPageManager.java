package com.yuantiku.yyl.pages;

import com.yuantiku.yyl.helper.L;
import com.yuantiku.yyl.pages.interfaces.Page;
import com.yuantiku.yyl.pages.interfaces.PageManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;

/**
 * @author wanghb
 * @date 15/5/5.
 */
public abstract class AbstractPageManager implements PageManager {

    private List<Page> pages = new ArrayList<>();

    @Override
    public void push(Page page, String tag) {
        L.i("push page " + tag + " : " + page.getClass().getName());
        page.setPageManager(this);
        page.prePush();
        pushPage(page, tag);
        pages.add(page);
        page.postPush();
    }

    @Override
    public void pop(Page page) {
        L.i("pop page " + " : " + page.getClass().getName());
        page.prePop();
        pages.remove(page);
        popPage(page);
        page.prePop();
    }

    @Override
    public void resetWithStartPage(Page page, String tag) {
        L.i("reset with start page " + tag + ":" + page.getClass().getName());
        List<Page> pageList = new ArrayList<>(pages);
        Collections.reverse(pageList);
        Observable.from(pageList).subscribe(pg -> pop(pg));
        push(page, tag);
    }

    protected abstract void pushPage(Page page, String tag);

    protected abstract void popPage(Page page);
}
