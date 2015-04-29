package com.yuantiku.yyl.observe;

/**
 * @author wanghb
 * @date 15/4/29.
 */
public interface MyObserver extends java.util.Observer {
    void update(MyObservable myObservable, Object data);
}
