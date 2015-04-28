package com.yuantiku.yyl.webadapter;

import retrofit.RequestInterceptor;

/**
 * @author lirui
 * @date 15/4/28.
 */
public class ApiHeaders implements RequestInterceptor {

    private String sessionId;

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void clearSessionId() {
        sessionId = null;
    }

    @Override
    public void intercept(RequestFacade request) {
        if (sessionId != null) {
            request.addHeader("Cookie", sessionId);
        }
    }
}
