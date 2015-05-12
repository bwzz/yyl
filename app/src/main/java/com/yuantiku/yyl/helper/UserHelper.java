package com.yuantiku.yyl.helper;

/**
 * @author lirui
 * @date 15/5/12.
 */
public enum UserHelper {
    INSTANCE;

    private PrefHelper helper;
    private final String TOKEN = "token";

    UserHelper(){
        helper = PrefHelper.get();
    }

    public void saveToken(String token){
        helper.put(TOKEN, token);
    }

    public String getToken(){
        return helper.getString(TOKEN, "");
    }
}
