/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.exception;

import java.io.Serializable;

import com.tuniu.ord.enums.OrdError;

/**
 * ord系统自定义异常
 * 
 * @author zhoujie8
 * 
 */
public class OrdCustomException extends RuntimeException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1137133653483514943L;

    /**
     * 错误编码
     */
    private int errCode;
    /**
     * 错误信息
     */
    private String message;

    public OrdCustomException() {

    }

    public OrdCustomException(OrdError error) {
        super();
        this.setErrCode(error.getCode());
        this.setMessage(error.getMsg());
    }

    public OrdCustomException(Throwable cause) {
        super(cause);
    }

    public OrdCustomException(String message) {
        super();
        this.setMessage(message);
    }

    public OrdCustomException(OrdError error, Throwable cause) {
        super(cause);
        this.setErrCode(error.getCode());
        this.setMessage(error.getMsg());
    }

    public OrdCustomException(OrdError error, String... message) {
        this.setErrCode(error.getCode());
        StringBuffer sb = new StringBuffer();
        for (String msg : message) {
            sb.append(msg);
        }
        this.setMessage(sb.toString());
    }

    public OrdCustomException(Throwable cause, String... message) {
        super(cause);
        StringBuffer sb = new StringBuffer();
        for (String msg : message) {
            sb.append(msg);
        }
        this.setMessage(sb.toString());
    }

    public OrdCustomException(OrdError error, Throwable cause, String... message) {
        super(cause);
        this.setErrCode(error.getCode());
        StringBuffer sb = new StringBuffer();
        for (String msg : message) {
            sb.append(msg);
        }
        this.setMessage(sb.toString());
    }

    /**
     * @return the errCode
     */
    public int getErrCode() {
        return errCode;
    }

    /**
     * @param errCode
     *            the errCode to set
     */
    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
