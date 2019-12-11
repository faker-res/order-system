/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fangzhongwei
 * 
 */
public class OrderRelationResultVo implements Serializable {

    private static final long serialVersionUID = 5564645491216650411L;

    /**
     * 销售订单号
     */
    private Integer id;

    /**
     * 切位单号
     */
    private Integer orderId;

    /**
     * 渠道订单号
     */
    private Integer extOrderId;

    /**
     * 渠道名称
     */
    private String saleChannel;

    private Integer dotaProductId;

    /**
     * 产品编号
     */
    private Integer productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 团模板号
     */
    private Integer tourTemplateId;

    /**
     * 团号
     */
    private Integer tourId;

    /**
     * 销售单状态
     */
    private String statusCode;

    /**
     * 团期
     */
    private Date startDate;

    /**
     * 成人数
     */
    private Integer adultCount;

    /**
     * 儿童数
     */
    private Integer childCount;

    /**
     * 成人价
     */
    private BigDecimal adultPrice;

    /**
     * 儿童价
     */
    private BigDecimal childPrice;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 单房差数量
     */
    private Integer roomAddNum;

    /**
     * 单房差
     */
    private BigDecimal roomAddPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getExtOrderId() {
        return extOrderId;
    }

    public void setExtOrderId(Integer extOrderId) {
        this.extOrderId = extOrderId;
    }

    public String getSaleChannel() {
        return saleChannel;
    }

    public void setSaleChannel(String saleChannel) {
        this.saleChannel = saleChannel;
    }

    public Integer getDotaProductId() {
        return dotaProductId;
    }

    public void setDotaProductId(Integer dotaProductId) {
        this.dotaProductId = dotaProductId;
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

    public Integer getTourTemplateId() {
        return tourTemplateId;
    }

    public void setTourTemplateId(Integer tourTemplateId) {
        this.tourTemplateId = tourTemplateId;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BigDecimal getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getRoomAddNum() {
        return roomAddNum;
    }

    public void setRoomAddNum(Integer roomAddNum) {
        this.roomAddNum = roomAddNum;
    }

    public BigDecimal getRoomAddPrice() {
        return roomAddPrice;
    }

    public void setRoomAddPrice(BigDecimal roomAddPrice) {
        this.roomAddPrice = roomAddPrice;
    }

}
