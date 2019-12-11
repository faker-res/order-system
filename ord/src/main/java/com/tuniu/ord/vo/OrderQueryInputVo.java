/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.Date;

/**
 * 【订单查询条件】
 * 
 * @author zhairongping
 *
 */
public class OrderQueryInputVo extends BaseQueryVo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer orderId;
    private String statusCode;
    private Integer customerStaffId;
    private Integer customerManagerId;
    private Integer productStaffId;
    private Integer productManagerId;
    private Integer operationStaffId;
    private Integer operationManagerId;
    private Integer productId;
    private String productName;
    private Date orderStartDate;
    private Date orderEndDate;
    private Date departStartDate;
    private Date departEndDate;
    private Integer destCategoryId;
    private Integer firstDestGroupId;
    private Integer secDestGroupId;
    private Integer sortColumn;

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

    public Integer getCustomerStaffId() {
        return customerStaffId;
    }

    public void setCustomerStaffId(Integer customerStaffId) {
        this.customerStaffId = customerStaffId;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

    public Integer getProductStaffId() {
        return productStaffId;
    }

    public void setProductStaffId(Integer productStaffId) {
        this.productStaffId = productStaffId;
    }

    public Integer getProductManagerId() {
        return productManagerId;
    }

    public void setProductManagerId(Integer productManagerId) {
        this.productManagerId = productManagerId;
    }

    public Integer getOperationStaffId() {
        return operationStaffId;
    }

    public void setOperationStaffId(Integer operationStaffId) {
        this.operationStaffId = operationStaffId;
    }

    public Integer getOperationManagerId() {
        return operationManagerId;
    }

    public void setOperationManagerId(Integer operationManagerId) {
        this.operationManagerId = operationManagerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getOrderStartDate() {
        return orderStartDate;
    }

    public void setOrderStartDate(Date orderStartDate) {
        this.orderStartDate = orderStartDate;
    }

    public Date getOrderEndDate() {
        return orderEndDate;
    }

    public void setOrderEndDate(Date orderEndDate) {
        this.orderEndDate = orderEndDate;
    }

    public Date getDepartStartDate() {
        return departStartDate;
    }

    public void setDepartStartDate(Date departStartDate) {
        this.departStartDate = departStartDate;
    }

    public Date getDepartEndDate() {
        return departEndDate;
    }

    public void setDepartEndDate(Date departEndDate) {
        this.departEndDate = departEndDate;
    }

    public Integer getDestCategoryId() {
        return destCategoryId;
    }

    public void setDestCategoryId(Integer destCategoryId) {
        this.destCategoryId = destCategoryId;
    }

    public Integer getFirstDestGroupId() {
        return firstDestGroupId;
    }

    public void setFirstDestGroupId(Integer firstDestGroupId) {
        this.firstDestGroupId = firstDestGroupId;
    }

    public Integer getSecDestGroupId() {
        return secDestGroupId;
    }

    public void setSecDestGroupId(Integer secDestGroupId) {
        this.secDestGroupId = secDestGroupId;
    }

    public Integer getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(Integer sortColumn) {
        this.sortColumn = sortColumn;
    }

}
