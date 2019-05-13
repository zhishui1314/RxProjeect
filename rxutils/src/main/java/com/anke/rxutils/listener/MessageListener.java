package com.anke.rxutils.listener;

/**
 * 创建者   张蔡奇
 * 创建时间 2019/1/16 8:57
 * 创建公司 珠海市安克电子技术有限公司
 * 数据回调结果
 */
public interface MessageListener {
    /**
     * 成功结果
     *
     * @param result
     */
    void onSuccess(String result);

    /**
     * 失败结果
     *
     * @param error
     */
    void onError(String error);
}
