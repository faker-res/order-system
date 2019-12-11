/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-30                                                      
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
 * @CreateDate 2016-5-30 <br>
 */

public class ProductOccupyReqData {
    /**
     * clientFlag
     */
    private String clientFlag;

    /**
     * sessionId
     */
    private Integer sessionId;

    /**
     * directionId
     */
    private Integer directionId;

    /**
     * isGreedy
     */
    private boolean isGreedy;

    /**
     * orderId
     */
    private Integer orderId;

    /**
     * channelId
     */
    private Integer channelId;

    /**
     * channelName
     */
    private String channelName;

    /**
     * revertServiceName
     */
    private String revertServiceName;

    /**
     * dProducts
     */
    private List<OccupyReqDproducts> dProducts;

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
     * get directionId
     * 
     * @return Returns the directionId.<br>
     */
    public Integer getDirectionId() {
        return directionId;
    }

    /**
     * set directionId
     * 
     * @param directionId
     *            The directionId to set. <br>
     */
    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
    }

    /**
     * get isGreedy
     * 
     * @return Returns the isGreedy.<br>
     */
    public boolean getIsGreedy() {
        return isGreedy;
    }

    /**
     * set isGreedy
     * 
     * @param isGreedy
     *            The isGreedy to set. <br>
     */
    public void setIsGreedy(boolean isGreedy) {
        this.isGreedy = isGreedy;
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
     * get channelId
     * 
     * @return Returns the channelId.<br>
     */
    public Integer getChannelId() {
        return channelId;
    }

    /**
     * set channelId
     * 
     * @param channelId
     *            The channelId to set. <br>
     */
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    /**
     * get channelName
     * 
     * @return Returns the channelName.<br>
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * set channelName
     * 
     * @param channelName
     *            The channelName to set. <br>
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
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
    public List<OccupyReqDproducts> getdProducts() {
        return dProducts;
    }

    /**
     * set dProducts
     * 
     * @param dProducts
     *            The dProducts to set. <br>
     */
    public void setdProducts(List<OccupyReqDproducts> dProducts) {
        this.dProducts = dProducts;
    }

}
