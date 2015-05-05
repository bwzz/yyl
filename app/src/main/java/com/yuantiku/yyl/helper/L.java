package com.yuantiku.yyl.helper;

import android.util.Log;

/**
 * @author lirui
 * @date 15/4/29.
 */
public class L {

    private L() {}

    public static void v(Object... m) {
        log(Log.VERBOSE, join(m), null);
    }

    public static void w(Object... m) {
        log(Log.WARN, join(m), null);
    }

    public static void d(Object... m) {
        log(Log.DEBUG, join(m), null);
    }

    public static void i(Object... m) {
        log(Log.INFO, join(m), null);
    }

    public static void e(Object... m) {
        log(Log.ERROR, join(m), null);
        for (Object o : m) {
            if (o instanceof Exception) {
                log(Log.ERROR, null, (Throwable) o);
            }
        }

    }

    private static void log(int level, String content, Throwable throwable) {
        String tag = getTagName();
        switch (level) {
            case Log.ASSERT:
                Log.e(tag, content);
                break;
            case Log.INFO:
                Log.i(tag, content);
                break;
            case Log.DEBUG:
                Log.d(tag, content);
                break;
            case Log.WARN:
                Log.w(tag, content);
                break;
            case Log.VERBOSE:
                Log.v(tag, content);
                break;
            case Log.ERROR:
                Log.e(tag, content, throwable);
                break;
            default:
                Log.wtf(tag, content, throwable);
        }
    }

    private static String getTagName() {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[5];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        return tag;
    }

    private static String join(Object[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Object o : arr) {
            sb.append(String.valueOf(o));
        }
        return sb.toString();
    }

}
