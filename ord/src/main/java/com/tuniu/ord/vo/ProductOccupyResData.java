/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-30                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;


/**
 * <Description> <br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-5-30 <br>
 */

public class ProductOccupyResData {

    /**
     * sessionId
     */
    private Integer sessionId;

    private boolean success;

    private String msg;

    private Integer errorCode;

    /**
     * data
     */
    private ProductOccupyResMainData data;

    /**
     * get sessionId
     * 
     * @return Returns the sessionId.<br>
     */
    public Integer getSessionId() {
        return sessionId;
    }

    /**
     * set sessionId
     * 
     * @param sessionId
     *            The sessionId to set. <br>
     */
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * get data
     * 
     * @return Returns the data.<br>
     */
    public ProductOccupyResMainData getData() {
        return data;
    }

    /**
     * set data
     * 
     * @param data
     *            The data to set. <br>
     */
    public void setData(ProductOccupyResMainData data) {
        this.data = data;
    }

    /**
     * get success
     * 
     * @return Returns the success.<br>
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * set success
     * 
     * @param success
     *            The success to set. <br>
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * get msg
     * 
     * @return Returns the msg.<br>
     */
    public String getMsg() {
        return msg;
    }

    /**
     * set msg
     * 
     * @param msg
     *            The msg to set. <br>
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * get errorCode
     * 
     * @return Returns the errorCode.<br>
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * set errorCode
     * 
     * @param errorCode
     *            The errorCode to set. <br>
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

}
