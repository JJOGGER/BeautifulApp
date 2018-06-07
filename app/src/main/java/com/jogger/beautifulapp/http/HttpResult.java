package com.jogger.beautifulapp.http;

/**
 * Created by jogger on 2018/1/18.
 * 网络解析基类
 */

public class HttpResult<T> {
    private T data;
    private int code;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "data=" + data +
                ", code=" + code +
                '}';
    }
}
