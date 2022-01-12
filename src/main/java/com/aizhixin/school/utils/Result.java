package com.aizhixin.school.utils;

import java.io.Serializable;


@SuppressWarnings("all")
public class Result<T> implements Serializable {

    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private T data;

    public static <T> Result build(Integer status, String msg, T data) {
        return new Result(status, msg, data);
    }

    public static <T> Result ok(T data) {
        return new Result(data);
    }

    public static <T> Result ok() {
        return new Result(null);
    }

    public Result() {
    }

    public static Result build(Integer status, String msg) {
        return new Result(status, msg, null);
    }

    public Result(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Result(T data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}