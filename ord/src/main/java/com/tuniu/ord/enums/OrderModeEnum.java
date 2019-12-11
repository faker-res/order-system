/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月8日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 * 下单方式
 * 
 * @author zhairongping
 *
 */
public enum OrderModeEnum {
    /**
     * 人工下单
     */
    MANUAL_CREATE_ORDER("manualCreateOrderService"),
    /**
     * 系统下单
     */
    SYSTEM_CREATE_ORDER("systemCreateOrderService");

    private String orderMode;

    /**
     * @param orderMode
     */
    private OrderModeEnum(String orderMode) {
        this.orderMode = orderMode;
    }

    /**
     * @return the orderMode
     */
    public String getOrderMode() {
        return orderMode;
    }

    /**
     * @param orderMode
     *            the orderMode to set
     */
    public void setOrderMode(String orderMode) {
        this.orderMode = orderMode;
    }

}
