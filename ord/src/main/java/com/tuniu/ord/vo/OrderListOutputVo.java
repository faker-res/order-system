/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月22日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.Date;

/**
 * @author zhairongping
 * 
 */
public class OrderListOutputVo {
    private Integer id;
    private Date orderDate;
    private Integer orderId;
    private String num;
    private String productName;
    private String statusCode;
    private String statusName;
    /**
     * 团期
     */
    private String departDate;
    /**
     * 产品编号
     */
    private Integer productId;
    /**
     * 客服经理姓名
     */
    private String productManagerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    /**
     * @return the departDate
     */
    public String getDepartDate() {
        return departDate;
    }

    /**
     * @param departDate
     *            the departDate to set
     */
    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    /**
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return the productManagerName
     */
    public String getProductManagerName() {
        return productManagerName;
    }

    /**
     * @param productManagerName
     *            the productManagerName to set
     */
    public void setProductManagerName(String productManagerName) {
        this.productManagerName = productManagerName;
    }

}
