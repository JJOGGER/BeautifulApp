package com.jogger.beautifulapp.http;

/**
 * Created by jogger on 2018/1/18.
 * 网络解析基类
 */

public class HttpResult<T> {
    private T data;
    private int result;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "data=" + data +
                ", result=" + result +
                '}';
    }
}
