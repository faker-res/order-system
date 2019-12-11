/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月22日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhairongping
 *
 */
public class OrderOueryVo extends BaseQueryVo {

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
    private String orderStartDate;
    private String orderEndDate;
    private String departStartDate;
    private String departEndDate;
    private Date orderStartDateDB;
    private Date orderEndDateDB;
    private Date departStartDateDB;
    private Date departEndDateDB;
    private Integer destCategoryId;
    private Integer firstDestGroupId;
    private Integer secDestGroupId;
    private Integer sortColumn;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getOrderStartDate() {
        return orderStartDate;
    }

    public void setOrderStartDate(String orderStartDate) {
        this.orderStartDate = orderStartDate;
        if (null != orderStartDate && !"".equals(orderStartDate)) {
            try {
                this.orderStartDateDB = df.parse(orderStartDate.concat(" 00:00:00"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public String getOrderEndDate() {
        return orderEndDate;
    }

    public void setOrderEndDate(String orderEndDate) {
        this.orderEndDate = orderEndDate;
        if (null != orderEndDate && !"".equals(orderEndDate)) {
            try {
                this.orderEndDateDB = df.parse(orderEndDate.concat(" 23:59:59"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDepartStartDate() {
        return departStartDate;
    }

    public void setDepartStartDate(String departStartDate) {
        this.departStartDate = departStartDate;
        if (null != departStartDate && !"".equals(departStartDate)) {
            try {
                this.departStartDateDB = df.parse(departStartDate.concat(" 00:00:00"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDepartEndDate() {
        return departEndDate;
    }

    public void setDepartEndDate(String departEndDate) {
        this.departEndDate = departEndDate;
        if (null != departEndDate && !"".equals(departEndDate)) {
            try {
                this.departEndDateDB = df.parse(departEndDate.concat(" 23:59:59"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public Date getOrderStartDateDB() {
        return orderStartDateDB;
    }

    public Date getOrderEndDateDB() {
        return orderEndDateDB;
    }

    public Date getDepartStartDateDB() {
        return departStartDateDB;
    }

    public Date getDepartEndDateDB() {
        return departEndDateDB;
    }

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
