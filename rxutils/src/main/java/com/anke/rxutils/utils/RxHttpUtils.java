package com.anke.rxutils.utils;

import android.util.Log;

import com.anke.rxutils.body.UpLoadObserver;
import com.anke.rxutils.body.UploadFileRequestBody;
import com.anke.rxutils.listener.CallBackListener;
import com.anke.rxutils.listener.MessageListener;
import com.anke.rxutils.listener.ProgressListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 创建者   张蔡奇
 * 创建时间 2019/1/16 9:04
 * 创建公司 珠海市安克电子技术有限公司
 */
public class RxHttpUtils {
    /**
     * get请求带参数的  分装好的回调在主线程
     *
     * @param url             网页链接
     * @param map             参数集合
     * @param messageListener 回调
     */
    public static void getMethod(String url, Map<String, String> map, final MessageListener messageListener) {
        RetrofitManager.getInstance().getMethod(url, map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (responseBody != null) {
                            try {
                                messageListener.onSuccess(responseBody.string());
                            } catch (IOException e) {
                                messageListener.onError("处理数据异常");
                                e.printStackTrace();
                            }
                        } else {
                            messageListener.onError("从服务端获取数据为空");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        messageListener.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    /**
     * get请求不带参数的  分装好的回调在主线程
     * @param url             网页链接
     * @param messageListener 回调
     */
    public static void getMethod(String url, final MessageListener messageListener) {
        RetrofitManager.getInstance().getMethod(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (responseBody != null) {
                            try {
                                messageListener.onSuccess(responseBody.string());
                            } catch (IOException e) {
                                messageListener.onError("处理数据异常");
                                e.printStackTrace();
                            }
                        } else {
                            messageListener.onError("从服务端获取数据为空");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        messageListener.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 原生
     * 此方法可以自由的控制子线程主线程
     * get请求带参数
     * @param url 网页链接
     * @param map 参数集合
     * @return
     */
    public static Observable<ResponseBody> getMethod(String url, Map<String, String> map) {
        return RetrofitManager.getInstance().getMethod(url, map);
    }
    /**
     * 原生
     * 此方法可以自由的控制子线程主线程
     * get请求不带参数
     * @param url 网页链接
     * @return
     */
    public static Observable<ResponseBody> getMethod(String url) {
        return RetrofitManager.getInstance().getMethod(url);
    }
    /**
     * post请求
     * @param url             网页链接
     * @param map             参数集合
     * @param messageListener 回调在主线程
     * @param messageListener
     */
    public static void postMethod(String url, Map<String, String> map, final MessageListener messageListener) {
        RetrofitManager.getInstance().postMethod(url, map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (responseBody != null) {
                            try {
                                messageListener.onSuccess(responseBody.string());
                            } catch (IOException e) {
                                messageListener.onError("处理数据异常");
                                e.printStackTrace();
                            }
                        } else {
                            messageListener.onError("从服务端获取数据为空");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        messageListener.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 原生
     * 此方法可以自由的控制子线程主线程
     * post请求
     *
     * @param url 网页链接
     * @param map 参数集合
     * @return
     */
    public static Observable<ResponseBody> postMethod(String url, Map<String, String> map) {
        return RetrofitManager.getInstance().getMethod(url, map);
    }

    /**
     * 文件的下载  全部在子线场中
     *
     * @param url              链接
     * @param path             下载地址
     * @param fileName         文件名字
     * @param callBackListener
     */
    public static void downLoadFile(String url, final String path, final String fileName, final CallBackListener callBackListener) {
        RetrofitManager.getInstance().getLargeFile(url)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        FileOutputStream fileOutputStream = null;
                        long total = responseBody.contentLength();
                        InputStream inputStream = responseBody.byteStream();
                        byte[] buff = new byte[1024];
                        int length;
                        int sum = 0;
                        try {
                            File file = new File(path);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            fileOutputStream = new FileOutputStream(new File(path + "/" + fileName));
                            while ((length = inputStream.read(buff)) != -1) {
                                sum = sum + length;//循环一次获取一次数据大小
                                fileOutputStream.write(buff, 0, length);
                                callBackListener.onProgress(sum, total);
                            }
                            fileOutputStream.flush();
                        } catch (Exception e) {
                            callBackListener.onError(e.getMessage());
                            e.printStackTrace();
                        } finally {
                            try {
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                            } catch (Exception e) {
                                callBackListener.onError(e.getMessage());
                                e.printStackTrace();
                            }


                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBackListener.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        callBackListener.finish("");
                    }
                });
    }


    /**
     * 带进度的上传大型文件
     *
     * @param files         file数组
     * @param formFiledName 服务端参数数组
     * @param map           参数集合
     */
    public static void upLoadFile(String url, File[] files, String[] formFiledName, Map<String, String> map, final CallBackListener callBackListener) {
        UpLoadObserver<ResponseBody> upLoadObserver = new UpLoadObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody value) {
                try {
                    callBackListener.finish(value.string());
                } catch (IOException e) {
                    callBackListener.onError("处理数据异常");
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                callBackListener.onError(e.getMessage());
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onProgressChange(long bytesWritten, long contentLength) {
                callBackListener.onProgress(bytesWritten, contentLength);
            }
        };
        RetrofitManager.getInstance().postUploadFilesMultipartBody(url, buildMultipartBody(map, files, formFiledName, upLoadObserver))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(upLoadObserver);
    }

    /**
     * 组合普通控件 与 file控件
     *
     * @param map           普通 控件 的名称  普通控件的值
     * @param files         上传文件 file文件
     * @param formFiledName file控件名称
     * @param upLoadOb      自己封装的UpLoadObserver
     * @return
     */
    private static MultipartBody buildMultipartBody(Map<String, String> map, File[] files, String[] formFiledName, final UpLoadObserver upLoadOb) {

        MultipartBody.Builder builder = new MultipartBody.Builder();
        //往MultipartBuilder对象中添加普通input控件的内容
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                //添加普通input块的数据
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                        RequestBody.create(null, entry.getValue()));
            }
        }
        //往MultipartBuilder对象中添加上传控件的内容
        if (files != null && formFiledName != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String fileName = file.getName();
                //RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                UploadFileRequestBody requestBody = new UploadFileRequestBody(file, new ProgressListener() {
                    @Override
                    public void onProgress(long progress, long total, boolean done) {
                        //progressDialog.setProgress((int)(progress * 100/total));
                        upLoadOb.onProgressChange(progress, total);
                    }
                });
                //添加file input块的数据
                builder.addPart(Headers.of("Content-Disposition",
                        "form-data; name=\"" + formFiledName[i] + "\"; filename=\"" + fileName + "\""), requestBody);
            }
        }
        return builder.build();
    }
}
