package com.xuwuji.tuding.utillib.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import java.io.File;
import java.util.Map;


/**
 * `Author: Saint
 * Time:
 * ReadMe:
 */
public class Api {
    private static Api mApi;
    private HttpParams params;


    private Api() {
        OkUtil.instance();
        if (params == null) {
            params = new HttpParams();
        }
    }

    public static Api instance() {
        if (mApi == null) {
            synchronized (Api.class) {
                if (mApi == null) {
                    mApi = new Api();
                }
            }
        }
        return mApi;
    }

    public void cancelTag(Object tag) {
        OkUtil.instance().cancelTag(tag);
    }

    private void postData(String api, Map<String, String> params, Object tag, StringCallback callback) {
        OkGo.<String>post(api)
                .params(params)
                .tag(tag)
                .execute(callback);
    }

    private void getData(String api, Map<String, String> params, Object tag, StringCallback callback) {
        OkGo.<String>get(api)
                .params(params)
                .tag(tag)
                .execute(callback);
    }

    private void putData(String api, Map<String, String> params, Object tag, StringCallback callback) {
        OkGo.<String>put(api)
                .params(params)
                .tag(tag)
                .execute(callback);
    }

    private void deleteData(String api, Map<String, String> params, Object tag, StringCallback callback) {
        OkGo.<String>delete(api)
                .tag(tag)
                .isSpliceUrl(true)
                .params(params)
                .execute(callback);
    }

    public void download(Object tag, String url, FileCallback callback) {
        OkGo.<File>post(url)
                .tag(tag)
                .execute(callback);
    }

}
