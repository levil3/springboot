package com.spt.springboot.exceptions;

/**
 * 异常类
 */
public class ParamsException extends RuntimeException{
    private Integer code = 500;
    private String msg = "参数异常";

    public ParamsException() {
        super("参数异常!");
    }

    public ParamsException(String message) {
        super(message);
        this.msg = message;
    }

    public ParamsException(String message, Integer code) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
