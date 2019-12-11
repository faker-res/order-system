/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu Nov 17 17:18:59 CST 2016
 * Description:
 */
package com.tuniu.ord.domain;

/**
 * OrdChannelOrder ord_channel_order
 */
public class OrdChannelOrder extends BaseDomain {
    /**
     * 外部订单号 ord_channel_order.channel_order_id
     */
    private Integer channelOrderId;

    /**
     * 订单处理状态：0:未处理 1已处理 ord_channel_order.status
     */
    private Integer status;

    /**
     * 原始参数 ord_channel_order.original_param
     */
    private String originalParam;

    /**
     * 操作类型：1：发起确认 2：发起核损 ord_channel_order.operator_type
     */
    private Integer operatorType;

    /**
     * 变更前出游人
     */
    private String beforeChangeTourist;

    /**
     * 变更后出游人
     */
    private String afterChangeTourist;

    /**
     * @return the channelOrderId
     */
    public Integer getChannelOrderId() {
        return channelOrderId;
    }

    /**
     * @param channelOrderId
     *            the channelOrderId to set
     */
    public void setChannelOrderId(Integer channelOrderId) {
        this.channelOrderId = channelOrderId;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the originalParam
     */
    public String getOriginalParam() {
        return originalParam;
    }

    /**
     * @param originalParam
     *            the originalParam to set
     */
    public void setOriginalParam(String originalParam) {
        this.originalParam = originalParam;
    }

    /**
     * @return the operatorType
     */
    public Integer getOperatorType() {
        return operatorType;
    }

    /**
     * @param operatorType
     *            the operatorType to set
     */
    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    /**
     * @return the beforeChangeTourist
     */
    public String getBeforeChangeTourist() {
        return beforeChangeTourist;
    }

    /**
     * @param beforeChangeTourist
     *            the beforeChangeTourist to set
     */
    public void setBeforeChangeTourist(String beforeChangeTourist) {
        this.beforeChangeTourist = beforeChangeTourist;
    }

    /**
     * @return the afterChangeTourist
     */
    public String getAfterChangeTourist() {
        return afterChangeTourist;
    }

    /**
     * @param afterChangeTourist
     *            the afterChangeTourist to set
     */
    public void setAfterChangeTourist(String afterChangeTourist) {
        this.afterChangeTourist = afterChangeTourist;
    }

}