package com.yuantiku.yyl.data;

import java.util.Map;

/**
 * @author lirui
 * @date 15/5/12.
 */
public class ResultList extends BaseData {
    private Map<String, String> data;
    private String detail;

    public Map<String, String> getData() {
        return data;
    }

    public String getDetail() {
        return detail;
    }
}
