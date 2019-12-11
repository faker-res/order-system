/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月28日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author fangzhongwei
 * 
 */
public class GroupOrderTourResultVo {

    private Integer orderId;

    private Integer tourBasicId;

    private Integer tourGroupId;

    private Integer productId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTourBasicId() {
        return tourBasicId;
    }

    public void setTourBasicId(Integer tourBasicId) {
        this.tourBasicId = tourBasicId;
    }

    public Integer getTourGroupId() {
        return tourGroupId;
    }

    public void setTourGroupId(Integer tourGroupId) {
        this.tourGroupId = tourGroupId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

}
