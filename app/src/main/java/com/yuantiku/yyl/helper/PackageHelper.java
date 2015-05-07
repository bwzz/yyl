package com.yuantiku.yyl.helper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * @author lirui
 * @date 15/5/7.
 */
public class PackageHelper {

    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    private static PackageInfo getPackageInfo(Context context) {
        try {
            return getPackageManager(context).getPackageInfo(context.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return new PackageInfo();
    }

    private static PackageManager getPackageManager(Context context) {
        PackageManager pm = context.getPackageManager();
        return pm;
    }
}