package com.yuantiku.yyl.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.yuantiku.yyl.data.VersionInfo;
import com.yuantiku.yyl.helper.DialogHelper.ConfirmCallback;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.http.GET;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author lirui
 * @date 15/5/7.
 */
public enum UpdateHelper {
    INSTANCE;

    private UpdateService service;

    UpdateHelper() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://fir.im/api/v2/app")
                .setLogLevel(LogLevel.FULL)
                .build();
        this.service = restAdapter.create(UpdateService.class);
    }

    public void checkUpdate(Context context) {
        service.getVersionInfo()
                .filter(VersionInfo::canUpdate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(versionInfo -> {
                    DialogHelper.showConfirm(context, "发现新版本", versionInfo.getChangelog(),
                            new ConfirmCallback() {
                        @Override
                        public String getPositiveHint() {
                            return "更新";
                        }

                        @Override
                        public void onPositive(DialogInterface dialog) {
                            update(context, versionInfo.getUpdate_url());
                        }

                        @Override
                        public String getNegativeHint() {
                            return "暂不更新";
                        }

                        @Override
                        public void onNegative(DialogInterface dialog) {
                        }
                    });
                }, Throwable::printStackTrace);
    }

    public void update(Context context, String url) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        } catch (Exception e) {
            L.e(e);
        }
    }

    public interface UpdateService {

        @GET("/version/554ad2aecfc28cc91c0002f0")
        Observable<VersionInfo> getVersionInfo();

    }
}
