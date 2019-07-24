package com.xuwuji.tuding.utillib.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.xuwuji.tuding.utillib.AppConfig;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * `Author: Saint
 * Time:
 * ReadMe:
 */
public class OkUtil {
    private static final long CONNECT_TIME_OUT = 10000;
    private static final long WRITE_TIME_OUT = 10000;
    private static final long READ_TIME_OUT = 10000;
    private static final String TAG = "HTTP_TU";


    private static OkGo mOkGo;

    private OkUtil() {

    }

    public static OkGo instance() {
        if (mOkGo == null) {
            synchronized (OkUtil.class) {
                if (mOkGo == null) {
                    init();
                }
            }
        }
        return mOkGo;
    }


    private static void init() {
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        //配置打印Log
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(TAG);
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.WARNING);
        mBuilder.addInterceptor(loggingInterceptor);
        //配置超时时间
        mBuilder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS);
        mBuilder.readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS);
        mBuilder.writeTimeout(WRITE_TIME_OUT, TimeUnit.MILLISECONDS);

        //配置Https
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        //方法二：自定义信任规则，校验服务端证书
//        HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
        //方法三：使用预埋证书，校验服务端证书（自签名证书）
//        HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(App.getInstance().getAssets().open("srca.cer"));
        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
//        HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
        mBuilder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
//        builder.hostnameVerifier(new SafeHostnameVerifier());

        //配置全局头参数
//        HttpHeaders headers = new HttpHeaders();
//        headers.put("key","value");

        //配置全局参数
//        HttpParams params = new HttpParams();
//        params.put("key", "value");

        mOkGo = OkGo.getInstance().init(AppConfig.getApp())
                .setOkHttpClient(mBuilder.build())
                .setCacheMode(CacheMode.NO_CACHE)
                .setRetryCount(0)
//                .addCommonHeaders(headers)
//                .addCommonParams(params)
        ;

    }
}
