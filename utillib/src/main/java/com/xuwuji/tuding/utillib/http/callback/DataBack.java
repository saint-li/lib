package com.xuwuji.tuding.utillib.http.callback;

import android.app.Activity;
import android.content.Intent;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xuwuji.tuding.utillib.AppConfig;
import com.xuwuji.tuding.utillib.util.AppToast;
import com.xuwuji.tuding.utillib.util.AppUtil;
import com.xuwuji.tuding.utillib.util.GsonUtil;
import com.xuwuji.tuding.utillib.util.LoadingDialog;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

public abstract class DataBack extends StringCallback {
    private Activity act;

    public DataBack() {
    }

    public DataBack(Activity activity) {
        act = activity;
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        request.headers("device", "3");
        request.headers("version", AppUtil.getVersionName());
        if (!AppUtil.isStringNull(AppConfig.token)) {
            request.headers("token", AppConfig.token);
        }
        if (act != null) LoadingDialog.show(act);
    }

    @Override
    public void onSuccess(Response<String> response) {
        SimpleResponse simple = GsonUtil.instance().fromJson(response.body(), SimpleResponse.class);
        if (simple.status == 101 || simple.status == 102 || simple.status == 103 || simple.status == 104) {
            //101，102,103 未登录或登录超时 104无学生操作权限
//            Intent intent = new Intent(AppConfig.app, LoginFailure.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra("message", simple.message);
//            AppConfig.app.startActivity(intent);
        } else if (onResult(simple.status, simple.message)) {

        } else if (simple.status == 200) {
            onResult(response.body());
        } else {
            AppToast.tShort(simple.message);
        }
    }

    //结果返回--字符串
    protected void onResult(String result) {
    }

    protected boolean onResult(int code, String msg) {
        return false;
    }

    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        if (response.getException() instanceof SocketTimeoutException) {
            AppToast.tShort("网络连接超时");
        } else if (response.getException() instanceof TimeoutException) {
            AppToast.tShort("网络连接超时");
        } else {
            AppToast.tShort(response.getException().getMessage());
        }
    }


    @Override
    public void onFinish() {
        super.onFinish();
        if (act != null) LoadingDialog.dismiss();
    }
}
