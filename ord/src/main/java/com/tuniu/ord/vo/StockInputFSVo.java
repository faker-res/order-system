/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年4月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * @author zhairongping
 *
 */
public class StockInputFSVo {
    /**
     * 客户端标识符,标识
     */
    private String clientFlag;
    /**
     * 会话Id, 用来唯一确定一次请求>0
     */
    private Integer sessionId;
    /**
     * D类切位单号
     */
    private Integer orderId;
    /**
     * 渠道号
     */
    private Integer channelId;
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 业务类型(前两位表示业务类型,10-A,11-D;后两位表示存放数据的类型:10-资源,11-产品)
     */
    private Integer businessType;
    /**
     * 入即占产品列表
     */
    private List<DProFSVo> dProducts;

    /**
     * @return the clientFlag
     */
    public String getClientFlag() {
        return clientFlag;
    }

    /**
     * @param clientFlag
     *            the clientFlag to set
     */
    public void setClientFlag(String clientFlag) {
        this.clientFlag = clientFlag;
    }

    /**
     * @return the sessionId
     */
    public Integer getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId
     *            the sessionId to set
     */
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     *            the orderId to set
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the channelId
     */
    public Integer getChannelId() {
        return channelId;
    }

    /**
     * @param channelId
     *            the channelId to set
     */
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    /**
     * @return the channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * @param channelName
     *            the channelName to set
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * @return the businessType
     */
    public Integer getBusinessType() {
        return businessType;
    }

    /**
     * @param businessType
     *            the businessType to set
     */
    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    /**
     * @return the dProducts
     */
    public List<DProFSVo> getdProducts() {
        return dProducts;
    }

    /**
     * @param dProducts
     *            the dProducts to set
     */
    public void setdProducts(List<DProFSVo> dProducts) {
        this.dProducts = dProducts;
    }

}
