package com.tuniu.ord.core.exception;

import com.tuniu.ord.enums.OrdError;

public class OrderSystemException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    private int errCode;
    /**
     * 错误信息
     */
    private String message;

    /**
     * 构造方法
     */
    public OrderSystemException() {

    }

    public OrderSystemException(OrdError ordError) {
        super();
        this.errCode = ordError.getCode();
        this.message = ordError.getMsg();
    }

    public OrderSystemException(int errCode, String message) {
        super();
        this.errCode = errCode;
        this.message = message;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
