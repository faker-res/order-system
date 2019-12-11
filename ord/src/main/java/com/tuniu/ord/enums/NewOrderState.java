/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月22日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 * 【订单状态枚举】
 * 
 * @author zhairongping
 *
 */
public enum NewOrderState {
    ORDERS("0", "接单"), REQUIREMENT_CONFIRM("10", "需求确认"), CONTRACT_PAYMENT("20", "签约付款"), CONFIRM("30", "确认"), NOTICE("40",
            "出团通知");

    private String statusCode;
    private String statusName;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    private NewOrderState(String statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    /**
     * 根据订单状态编码获取订单状态
     * 
     * @param statusCode
     * @return
     */
    public static NewOrderState getOrderStateEnum(String statusCode) {
        for (NewOrderState os : NewOrderState.values()) {
            if (os.getStatusCode().equals(statusCode)) {
                return os;
            }
        }
        return null;
    }

}
