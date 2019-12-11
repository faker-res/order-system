package com.tuniu.ord.vo;

import com.tuniu.ord.common.constant.ProductResultCode;

/**
 * @author fangzhongwei
 * 
 */
public class ResponseVo {

    private boolean success = true;

    private int errorCode = ProductResultCode.SUCCESS_CODE.getKey();

    private String msg = ProductResultCode.SUCCESS_CODE.getMessage();

    private Object data;

    /*************************** 拓展新的字段 *********************/
    /**
     * API系统错误码
     */
    private Integer returnCode;

    /**
     * API系统提示信息
     */
    private String errorMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
