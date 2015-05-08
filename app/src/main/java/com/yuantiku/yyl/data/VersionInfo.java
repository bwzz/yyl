package com.yuantiku.yyl.data;

import com.yuantiku.yyl.MyApplication;
import com.yuantiku.yyl.helper.PackageHelper;

/**
 * @author lirui
 * @date 15/5/7.
 */
public class VersionInfo extends BaseData {

    private String name;
    private int version;
    private String changelog;
    private String versionShort;
    private String installUrl;
    private String update_url;

    public String getUpdate_url() {
        return update_url;
    }

    public String getInstallUrl() {
        return installUrl;
    }

    public String getVersionShort() {
        return versionShort;
    }

    public String getChangelog() {
        return changelog;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public boolean canUpdate(){
        return PackageHelper.getVersionCode(MyApplication.getInstance()) < version;
    }
}
