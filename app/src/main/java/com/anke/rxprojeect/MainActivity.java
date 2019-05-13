package com.anke.rxprojeect;

import android.Manifest;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anke.rxutils.listener.CallBackListener;
import com.anke.rxutils.listener.MessageListener;
import com.anke.rxutils.utils.RxHttpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    //post请求 id
    private String requestUrl = "http://172.16.100.58:8080/ssmSummary/addPeople";
    //get请求
    private String getUrl = "http://172.16.100.58:8080/ssmSummary/selectAllPeople";

    private String upLoadUrl = "http://192.168.76.110:8080/ssmpom/updateImg.action";
    private String apkUrl = "http://172.16.100.58:8080/ssmSummary/upload/PreHospitalNotify.apk";
    private String getURL = "http://192.168.253.1:8080/ssmpom/android/getUserById.action?id=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取权限
        new RxPermissions(this).request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE


        )
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {//权限全部通过

                        } else {//至少有一个
                        }
                    }
                });
    }

    //get请求
    public void btnGet(View view) {
     RxHttpUtils.getMethod(getURL, new MessageListener() {
         @Override
         public void onSuccess(String result) {
             Log.e("onSuccess",result);
         }

         @Override
         public void onError(String error) {
             Log.e("onError",error);
         }
     });
//        map.put("id", "1");
//        initGetSimple(map);
//        initGetNative(map);
    }

    //post请求 在主线程
    public void btnPost(View view) {
        Map<String, String> map = new HashMap<>();
//        map.put("id", "1");
        postSimple(map);
//        postNative(map);
    }

    /**
     * 下载视频
     * 在子线程
     *
     * @param view
     */
    public void btnDownload(final View view) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/123";
        RxHttpUtils.downLoadFile(apkUrl, path, "123.apk", new CallBackListener() {
            @Override
            public void onProgress(long progress, long total) {

                Log.e("下载日志", progress + ":" + total);
            }

            @Override
            public void onError(String error) {
                Log.e("onError", error);
            }

            @Override
            public void finish(String result) {

                Log.e("finish", "finish");
            }
        });
    }

    /**
     * 带进度上传
     *
     * @param view
     */
    public void btnUpload(View view) {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PreHospitalInner.apk";
        File[] files = new File[]{new File(filePath)};
        String[] formFiledName = new String[]{"pictureName"};
        Map<String, String> map = new HashMap<>();
        map.put("name", "张三");
        RxHttpUtils.upLoadFile(upLoadUrl, files, formFiledName, map, new CallBackListener() {
            @Override
            public void onProgress(long progress, long total) {
                Log.e("onProgress", progress + ":" + total);

            }

            @Override
            public void onError(String error) {
                Log.e("onError", error);
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();

            }

            @Override
            public void finish(String result) {
                Log.e("finish", result);
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 调用原生
     *
     * @param map
     */
    private void initGetNative(Map<String, String> map) {
        RxHttpUtils.getMethod(requestUrl, map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 调用封装好的，回调在主线程
     *
     * @param map
     */
    private void initGetSimple(Map<String, String> map) {
        RxHttpUtils.getMethod(requestUrl, map, new MessageListener() {
            @Override
            public void onSuccess(String result) {
                Log.e("get请求成功", result);
            }

            @Override
            public void onError(String error) {
                Log.e("get请求失败", error);
            }
        });
    }


    /**
     * post原生的
     *
     * @param map
     */
    private void postNative(Map<String, String> map) {
        RxHttpUtils.postMethod(requestUrl, map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 封装好简单的请求
     *
     * @param map
     */
    private void postSimple(Map<String, String> map) {
        RxHttpUtils.postMethod(requestUrl, map, new MessageListener() {
            @Override
            public void onSuccess(String result) {
                Log.e("get请求成功", result);
            }

            @Override
            public void onError(String error) {
                Log.e("get请求失败", error);
            }
        });
    }

}
