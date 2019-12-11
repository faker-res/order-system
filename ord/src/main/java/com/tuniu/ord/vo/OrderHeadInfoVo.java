/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月22日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 *
 */
public class OrderHeadInfoVo {
    private Integer orderId;
    private String statusCode;
    private String statusName;
    private Integer productStaffId;
    private String productStaffName;
    private Integer productManagerId;
    private String productManagerName;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

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

    public Integer getProductStaffId() {
        return productStaffId;
    }

    public void setProductStaffId(Integer productStaffId) {
        this.productStaffId = productStaffId;
    }

    public String getProductStaffName() {
        return productStaffName;
    }

    public void setProductStaffName(String productStaffName) {
        this.productStaffName = productStaffName;
    }

    public Integer getProductManagerId() {
        return productManagerId;
    }

    public void setProductManagerId(Integer productManagerId) {
        this.productManagerId = productManagerId;
    }

    public String getProductManagerName() {
        return productManagerName;
    }

    public void setProductManagerName(String productManagerName) {
        this.productManagerName = productManagerName;
    }

}
