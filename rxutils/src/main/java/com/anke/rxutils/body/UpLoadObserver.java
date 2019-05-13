package com.anke.rxutils.body;
import io.reactivex.observers.DefaultObserver;

/**
 * 作者  张蔡奇
 * 时间  2017/10/7
 */

public abstract class UpLoadObserver<T> extends DefaultObserver<T> {
    @Override
    public void onNext(T value) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    //监听进度的改变
    public void onProgressChange(long bytesWritten, long contentLength) {

    }
}
