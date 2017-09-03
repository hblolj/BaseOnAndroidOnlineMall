package com.app.baseonandroidonlinemall.api;

/**
 * Created by hblolj on 2017/5/14.
 */

public class HttpResult<T> {

    private String status;
    private String result;

    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
