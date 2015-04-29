package com.yuantiku.yyl.observe;

/**
 * @author wanghb
 * @date 15/4/29.
 */
public class MyObservable extends java.util.Observable {

    @Override
    public void notifyObservers(Object data) {
        setChanged();
        super.notifyObservers(data);
    }
}
