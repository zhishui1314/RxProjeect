package com.anke.rxutils.utils;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 作者  张蔡奇
 * 时间  2017/11/22
 */

public interface ApiService{
//    /**
//     * 通过传参数get请求
//     *
//     * @param map
//     * @return
//     */
//    @GET("android/getUserById.action")
//    Observable<User> findUser(@QueryMap Map<String, String> map);

    /**
     * 通过url get请求
     *
     * @param urlString
     * @return
     */
    @GET
    Observable<ResponseBody> getMethod(@Url String urlString);

    /**
     * 通过url+map获取get请求
     *
     * @param urlString
     * @param map
     * @return
     */
    @GET
    Observable<ResponseBody> getMethod(@Url String urlString, @QueryMap Map<String, String> map);

//    @GET
//    Observable<ResponseBody> getMethod1(@Url String urlString, @QueryMap Map<String, String> map);

    /**
     * 作用：访问网络，下载大文件。
     * 默认情况下，Retrofit在处理结果前会将服务器端的Response全部读进内存。
     * 如果服务器端返回的是一个非常大的文件，则容易oom。
     * 加上 @Streaming防止oom
     *
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> getLargeFile(@Url String urlString);

    /**
     * 作用：post网络请求，向服务器提交表单域数据
     * 像http://47.93.175.200:5009/  @POST没有任何参数（C# httplistener）用地址@url
     *
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postMethod(@Url String url, @FieldMap Map<String, String> map);

//    @FormUrlEncoded
//    @POST("android/getUserById.action")
//    Observable<User> getUserById(@FieldMap Map<String, String> map);

    /**
     * 作用：POST网络请求，上传多个文件，同时上传表单域数据
     *
     * @param
     * @return
     */
    @POST
    Observable<ResponseBody> postUploadFilesMultipartBody(@Url String url, @Body MultipartBody multipartBody);
}
