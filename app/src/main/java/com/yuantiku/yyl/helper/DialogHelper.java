package com.yuantiku.yyl.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
 * @author wanghb
 */
public class DialogHelper {

    public interface ConfirmCallback {

        String getPositiveHint();

        void onPositive(DialogInterface dialog);

        String getNegativeHint();

        void onNegative(DialogInterface dialog);
    }

    // 这里context必须是activity
    public static AlertDialog showConfirm(Context context, CharSequence title,
                                          CharSequence message, final ConfirmCallback callback) {
        return showConfirm(context, title, message, callback, true);
    }

    public static AlertDialog showConfirm(Context context, CharSequence title,
                                          CharSequence message, final ConfirmCallback callback, boolean cancelable) {
        if (context == null) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        OnClickListener ln = new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    callback.onPositive(dialog);
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    callback.onNegative(dialog);
                }
            }
        };
        return builder.setTitle(title).setMessage(message)
                .setPositiveButton(callback.getPositiveHint(), ln)
                .setNegativeButton(callback.getNegativeHint(), ln).setCancelable(cancelable).show();
    }
}
