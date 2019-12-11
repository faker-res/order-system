/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Mon Oct 17 10:26:20 CST 2016
 * Description:
 */
package com.tuniu.ord.domain;

/**
 * ordTransferOrder ord_transfer_order
 */
public class OrdTransferOrder extends BaseDomain {
    /**
     * A类订单号 ord_transfer_order.order_id
     */
    private Integer orderId;

    /**
     * 状态 ord_transfer_order.status
     */
    private String status;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}