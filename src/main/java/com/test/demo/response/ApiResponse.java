package com.test.demo.response;

import com.test.demo.exception.ExceptionEnum;

public class ApiResponse<T> {

    private String code;

    private String msg;

    private T t;

    public ApiResponse(String code, String errorMsg){
        this.code = code;
        this.msg = errorMsg;
    }

    public ApiResponse(T t){
        this.code = ExceptionEnum.SUCCESS.getCode();
        this.msg = ExceptionEnum.SUCCESS.getMsg();
        this.t = t;
    }

    public static ApiResponse error(String code, String errorMsg) {
        return new ApiResponse(code, errorMsg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
