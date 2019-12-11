/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-31                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * <Description> <br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-5-31 <br>
 */

public class ProductCancelOccupyReqData {

    /**
     * clientFlag
     */
    private String clientFlag;

    /**
     * sessionId
     */
    private Integer sessionId;

    /**
     * orderId
     */
    private Integer orderId;

    /**
     * revertServiceName
     */
    private String revertServiceName;

    /**
     * dProducts
     */
    private List<CancelOccupyReqDproducts> dProducts;

    /**
     * get clientFlag
     * 
     * @return Returns the clientFlag.<br>
     */
    public String getClientFlag() {
        return clientFlag;
    }

    /**
     * set clientFlag
     * 
     * @param clientFlag
     *            The clientFlag to set. <br>
     */
    public void setClientFlag(String clientFlag) {
        this.clientFlag = clientFlag;
    }

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
     * get orderId
     * 
     * @return Returns the orderId.<br>
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * set orderId
     * 
     * @param orderId
     *            The orderId to set. <br>
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * get revertServiceName
     * 
     * @return Returns the revertServiceName.<br>
     */
    public String getRevertServiceName() {
        return revertServiceName;
    }

    /**
     * set revertServiceName
     * 
     * @param revertServiceName
     *            The revertServiceName to set. <br>
     */
    public void setRevertServiceName(String revertServiceName) {
        this.revertServiceName = revertServiceName;
    }

    /**
     * get dProducts
     * 
     * @return Returns the dProducts.<br>
     */
    public List<CancelOccupyReqDproducts> getdProducts() {
        return dProducts;
    }

    /**
     * set dProducts
     * 
     * @param dProducts
     *            The dProducts to set. <br>
     */
    public void setdProducts(List<CancelOccupyReqDproducts> dProducts) {
        this.dProducts = dProducts;
    }

}
