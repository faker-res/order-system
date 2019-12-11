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
public enum OrderStateEnum {
    INITIAL("OS000", "初始状态"), OCCUPYING("OS001", "占位中"), OCCUPIED("OS002", "占位成功"), OCCUPYFAILED("OS003", "占位失败"), CONFIRMING(
            "OS004", "确认中"), CONFIRMED("OS005", "已确认"), CONFIRMFAILED("OS006",
                    "确认失败"), LOSSING("OS007", "核损中"), CONFIRMLOSS("OS008", "已反馈核损"), CANCLE_OVER("OS009", "已取消");
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

    private OrderStateEnum(String statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    /**
     * 根据订单状态编码获取订单状态
     * 
     * @param statusCode
     * @return
     */
    public static OrderStateEnum getOrderStateEnum(String statusCode) {
        for (OrderStateEnum os : OrderStateEnum.values()) {
            if (os.getStatusCode().equals(statusCode)) {
                return os;
            }
        }
        return null;
    }

}
