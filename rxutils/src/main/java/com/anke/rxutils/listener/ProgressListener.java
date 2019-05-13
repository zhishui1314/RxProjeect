package com.anke.rxutils.listener;

/**
 * 作者  张蔡奇
 * 时间  2017/10/7
 */

public interface ProgressListener {
    /**
     * @param progress     已经下载或上传字节数
     * @param total        总字节数
     * @param done         是否完成
     */
    void onProgress(long progress, long total, boolean done);
}
