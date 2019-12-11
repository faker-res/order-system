/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年8月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 *
 */
public class DetailVo {
    private Integer orderId;
    private Integer confirmedAdultCount;
    private Integer lossAdultCount;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getConfirmedAdultCount() {
        return confirmedAdultCount;
    }

    public void setConfirmedAdultCount(Integer confirmedAdultCount) {
        this.confirmedAdultCount = confirmedAdultCount;
    }

    public Integer getLossAdultCount() {
        return lossAdultCount;
    }

    public void setLossAdultCount(Integer lossAdultCount) {
        this.lossAdultCount = lossAdultCount;
    }

}
