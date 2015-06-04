package com.yuantiku.yyl.helper;

import android.graphics.Typeface;
import android.widget.TextView;

import com.yuantiku.yyl.MyApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghb
 * @date 15/6/3.
 */
public class ConstellationHelper {

    public static String[] names = new String[]{"水瓶", "双鱼", "魔羯", "处女", "狮子", "巨蟹", "天蝎", "射手",
            "天秤", "双子", "金牛", "白羊"};
    public static Integer[] codepoints = new Integer[]{0x3437, 0x3438, 0x3439, 0x343a, 0x343b, 0x343c,
            0x343d, 0x343e, 0x343f, 0x3440, 0x3441, 0x3442};
    private static final Map<String, Integer> constellationMap = new HashMap<>();

    private static Typeface constellationTypeface;

    static {
        constellationTypeface = Typeface.createFromAsset(MyApplication.getInstance().getAssets(),
                "iconfont/constellation.ttf");
        constellationMap.put("水瓶", 0x3437);
        constellationMap.put("双鱼", 0x3438);
        constellationMap.put("魔羯", 0x3439);
        constellationMap.put("摩羯", 0x3439);
        constellationMap.put("处女", 0x343a);
        constellationMap.put("狮子", 0x343b);
        constellationMap.put("巨蟹", 0x343c);
        constellationMap.put("天蝎", 0x343d);
        constellationMap.put("射手", 0x343e);
        constellationMap.put("天秤", 0x343f);
        constellationMap.put("天枰", 0x343f);
        constellationMap.put("双子", 0x3440);
        constellationMap.put("金牛", 0x3441);
        constellationMap.put("白羊", 0x3442);
    }

    public static String getIconFontString(String name) {
        Integer cp = constellationMap.get(name);
        if (cp == null) {
            L.e("code point lost for ", name);
            return name;
        } else {
            return String.valueOf(Character.toChars(cp));
        }
    }

    public static void setTextView(TextView textView, String name) {
        textView.setTypeface(constellationTypeface);
        textView.setText(getIconFontString(name));
    }

}
