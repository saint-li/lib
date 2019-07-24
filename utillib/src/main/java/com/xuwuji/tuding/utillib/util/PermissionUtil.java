package com.xuwuji.tuding.utillib.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

public class PermissionUtil {

    public static void request(Context context, Action onGranted, Action onDemied, String... permissions) {
        AndPermission.with(context)
                .runtime()
                .permission(permissions)
                .onGranted(onGranted)
                .onDenied(onDemied)
                .start();
    }

    public static void request(Activity activity, Action onGranted, Action onDemied, String... permissions) {
        AndPermission.with(activity)
                .runtime()
                .permission(permissions)
                .onGranted(onGranted)
                .onDenied(onDemied)
                .start();
    }

    public static void request(Fragment fragment, Action onGranted, Action onDemied, String... permissions) {
        AndPermission.with(fragment)
                .runtime()
                .permission(permissions)
                .onGranted(onGranted)
                .onDenied(onDemied)
                .start();
    }
}
