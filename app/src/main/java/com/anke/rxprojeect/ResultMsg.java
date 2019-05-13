package com.anke.rxprojeect;

/**
 * 创建者   张蔡奇
 * 创建时间 2019/1/23 10:27
 * 创建公司 珠海市安克电子技术有限公司
 */
public class ResultMsg {
    private int code;// 0 成功 1失败
    private String data;//成功返回的json数据
    private String errorMgs;//锁雾返回的错误原因

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getErrorMgs() {
        return errorMgs;
    }

    public void setErrorMgs(String errorMgs) {
        this.errorMgs = errorMgs;
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "code=" + code +
                ", data='" + data + '\'' +
                ", errorMgs='" + errorMgs + '\'' +
                '}';
    }
}
