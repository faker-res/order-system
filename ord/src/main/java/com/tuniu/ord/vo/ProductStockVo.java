/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * 【签约出库接口入参】 【签约出库接口出参】 【取消签约接口入参】
 * 
 * @author zhairongping
 *
 */
public class ProductStockVo {
    private String clientFlag;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Integer sessionId;
    private String revertServiceName;
    private List<DProductStock> dProducts;

    public String getClientFlag() {
        return clientFlag;
    }

    public void setClientFlag(String clientFlag) {
        this.clientFlag = clientFlag;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getRevertServiceName() {
        return revertServiceName;
    }

    public void setRevertServiceName(String revertServiceName) {
        this.revertServiceName = revertServiceName;
    }

    public List<DProductStock> getdProducts() {
        return dProducts;
    }

    public void setdProducts(List<DProductStock> dProducts) {
        this.dProducts = dProducts;
    }

}
