/*
 * *
 *  * Copyright 2012 fenbi.com. All rights reserved.
 *  * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.yuantiku.yyl.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.yuantiku.yyl.MyApplication;

/**
 * @author wanghb
 */
public class PrefUtil {

    private static final String defaultPrefName = "lib.pref";
    private static final String LAST_STAT_TIME = "lastStatTime";

    public static PrefUtil get() {
        return get(defaultPrefName);
    }

    public static PrefUtil get(String prefName) {
        return new PrefUtil(prefName);
    }

    public PrefUtil(String prefName) {
        this.prefName = prefName;
    }

    public PrefUtil put(String key, String value) {
        getPref().edit().putString(key, value).commit();
        return this;
    }

    public PrefUtil put(String key, int value) {
        getPref().edit().putInt(key, value).commit();
        return this;
    }

    public PrefUtil put(String key, boolean value) {
        getPref().edit().putBoolean(key, value).commit();
        return this;
    }

    public PrefUtil put(String key, float value) {
        getPref().edit().putFloat(key, value).commit();
        return this;
    }

    public PrefUtil put(String key, long value) {
        getPref().edit().putLong(key, value).commit();
        return this;
    }

    public float getFloat(String key, float defValue) {
        return getPref().getFloat(key, defValue);
    }

    public String getString(String key, String defValue) {
        return getPref().getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return getPref().getBoolean(key, defValue);
    }

    public long getLastStatTime(){
        return getPref().getLong(LAST_STAT_TIME, 0);
    }

    public void setLastStatTime(long lastStatTime){
        put(LAST_STAT_TIME, lastStatTime);
    }

    public int getInt(String key, int defValue) {
        return getPref().getInt(key, defValue);
    }

    public void clear() {
        getPref().edit().clear().commit();
    }

    private SharedPreferences pref;

    private String prefName = defaultPrefName;

    public SharedPreferences getPref() {
        if (pref == null) {
            pref = MyApplication.getInstance().getSharedPreferences(prefName,
                    Context.MODE_PRIVATE);
        }
        return pref;
    }
}
