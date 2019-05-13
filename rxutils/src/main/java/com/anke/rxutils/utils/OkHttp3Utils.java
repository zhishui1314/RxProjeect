package com.anke.rxutils.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * Created by steven on 16/3/17.
 */
public class OkHttp3Utils {
    private static OkHttpClient okHttpClient = null;

    public  static OkHttpClient getOkHttpSingletonInstance() {
        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient().newBuilder()
                            .connectTimeout(8, TimeUnit.SECONDS)//8S连接超时
                            //.readTimeout(20, TimeUnit.SECONDS)//20s读取超时
                            //.writeTimeout(20, TimeUnit.SECONDS)//20s写入超时
                            //错误重连
                            .retryOnConnectionFailure(false)
                            .build();

                }
            }
        }
        return okHttpClient;
    }

}
