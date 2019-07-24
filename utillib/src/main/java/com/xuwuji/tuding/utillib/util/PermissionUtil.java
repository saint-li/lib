package com.xuwuji.tuding.utillib.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

public class PermissionUtil {

    public static void request(Context context, Action<List<String>> onGranted, Action<List<String>> onDenied, String... permissions) {
        AndPermission.with(context)
                .runtime()
                .permission(permissions)
                .onGranted(onGranted)
                .onDenied(onDenied)
                .start();
    }

    public static void request(Activity activity, Action<List<String>> onGranted, Action<List<String>> onDenied, String... permissions) {
        AndPermission.with(activity)
                .runtime()
                .permission(permissions)
                .onGranted(onGranted)
                .onDenied(onDenied)
                .start();
    }

    public static void request(Fragment fragment, Action<List<String>> onGranted, Action<List<String>> onDenied, String... permissions) {
        AndPermission.with(fragment)
                .runtime()
                .permission(permissions)
                .onGranted(onGranted)
                .onDenied(onDenied)
                .start();
    }
}
