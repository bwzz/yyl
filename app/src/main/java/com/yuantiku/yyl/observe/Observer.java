package com.yuantiku.yyl.observe;

/**
 * @author wanghb
 * @date 15/4/29.
 */
public interface Observer extends java.util.Observer {
    void update(Observable observable, Object data);
}
