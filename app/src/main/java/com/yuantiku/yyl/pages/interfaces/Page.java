package com.yuantiku.yyl.pages.interfaces;

/**
 * @author wanghb
 * @date 15/5/5.
 */
public interface Page {
    void setPageManager(PageManager pageManager);

    void prePush();

    void postPush();

    void prePop();

    void postPop();
}
