/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author fangzhongwei
 * 
 */
public class OrdSalesPriceRelationVo extends BaseQueryVo {

    private static final long serialVersionUID = -3021707712459078788L;

    // 销售订单号
    // private Integer id;

    // 渠道订单号
    private Integer extOrderId;

    private String departStartDate;

    private String departEndDate;

    private String saleChannel;

    private Integer productId;

    private Integer orderId;

    private Integer dotaProductId;

    public Integer getExtOrderId() {
        return extOrderId;
    }

    public void setExtOrderId(Integer extOrderId) {
        this.extOrderId = extOrderId;
    }

    public String getDepartStartDate() {
        return departStartDate;
    }

    public void setDepartStartDate(String departStartDate) {
        this.departStartDate = departStartDate;
    }

    public String getDepartEndDate() {
        return departEndDate;
    }

    public void setDepartEndDate(String departEndDate) {
        this.departEndDate = departEndDate;
    }

    public String getSaleChannel() {
        return saleChannel;
    }

    public void setSaleChannel(String saleChannel) {
        this.saleChannel = saleChannel;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDotaProductId() {
        return dotaProductId;
    }

    public void setDotaProductId(Integer dotaProductId) {
        this.dotaProductId = dotaProductId;
    }

}
