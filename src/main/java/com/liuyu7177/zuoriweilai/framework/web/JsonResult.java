package com.liuyu7177.zuoriweilai.framework.web;

/**
 * Created by liuyu7177 On 2019/5/15
 */
public class JsonResult<T> {
    private boolean success;
    private T data;
    private String msg;

    public JsonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public JsonResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public JsonResult(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
