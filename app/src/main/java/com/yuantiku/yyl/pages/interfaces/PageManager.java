package com.yuantiku.yyl.pages.interfaces;

/**
 * @author wanghb
 * @date 15/5/5.
 */
public interface PageManager {

    void push(Page page, String tag);

    boolean interceptBackPressed();

    void pop(Page page);

    void resetWithStartPage(Page page, String tag);
}
