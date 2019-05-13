package com.anke.rxutils.utils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者  张蔡奇
 * 时间  2017/10/12
 */

public class RetrofitManager {

    private static ApiService myRetrofitInterface = null;

    /**
     * 单例模式
     *
     * @return
     */
    public static ApiService getInstance() {
        if (myRetrofitInterface == null) {
            synchronized (RetrofitManager.class) {
                myRetrofitInterface = new Retrofit.Builder()
                        .baseUrl("http://www.baidu.com/")
                        .client(OkHttp3Utils.getOkHttpSingletonInstance())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        //不加这句话  ResponseBody   加这句话自动解析解析相应的格式否则报错
//                        .addConverterFactory(GsonConverterFactory.create())//加这句话自动解析
                        .build()
                        .create(ApiService.class);
            }
        }
        return myRetrofitInterface;
    }
}
