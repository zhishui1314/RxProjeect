package com.anke.rxutils.listener;

/**
 * 创建者   张蔡奇
 * 创建时间 2019/1/16 11:15
 * 创建公司 珠海市安克电子技术有限公司
 */
public interface CallBackListener {
    /**
     * @param progress 已经下载或上传字节数
     * @param total    总字节数
     */
    void onProgress(long progress, long total);

    /**
     * 错误返回值
     *
     * @param error
     */
    void onError(String error);

    /**
     * 完成操作    下载为null 上传返回服务端的值
     */
    void finish(String result);

}
