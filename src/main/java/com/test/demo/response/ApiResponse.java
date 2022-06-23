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
}
