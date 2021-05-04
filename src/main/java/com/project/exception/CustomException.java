package com.project.exception;

/**
 * 通用自定义异常
 */
public class CustomException extends RuntimeException {
    private String msg;
    private int code = 500;

    public CustomException(String msg){
        super(msg);
        this.msg = msg;
    }

    public CustomException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
