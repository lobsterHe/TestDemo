package com.test.demo.exception;

public enum ExceptionEnum {

    SUCCESS("200","SUCCESS"),
    UNKNOWN("10000", "unknown exception!"),
    USER_NOT_EXIT("10001", "user does not exist!"),
    IS_NOT_NULL("10002","%s is not null."),
    NO_STOCK("10003","no stock."),
    ORDER_ID_IS_NOT_EXIST("10004","order is not exist."),
    END_TIME_MORE_THAN_BEGIN_TIME("10005","end time more than the begin time"),
    BEGIN_TIME_LESS_THAN_NOW_TIME("10006","begin time more than the current time"),
    NO_THIS_CAR("10007","without this car.");

    private String code;

    private String msg;

    ExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}