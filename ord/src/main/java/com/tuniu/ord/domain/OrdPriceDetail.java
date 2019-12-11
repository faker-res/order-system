/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Wed May 25 16:27:50 CST 2016
 * Description:
 */
package com.tuniu.ord.domain;

import java.math.BigDecimal;

/**
 * OrdPriceDetail ord_price_detail
 */
public class OrdPriceDetail extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 订单号 ord_price_detail.order_id
     */
    private Integer orderId;

    /**
     * 库存成人数 ord_price_detail.stock_adult_count
     */
    private Integer stockAdultCount;

    /**
     * 库存儿童数 ord_price_detail.stock_child_count
     */
    private Integer stockChildCount;

    /**
     * 成人价 ord_price_detail.adult_price
     */
    private BigDecimal adultPrice;

    /**
     * 儿童价 ord_price_detail.child_price
     */
    private BigDecimal childPrice;

    /**
     * 已确认成人数 ord_price_detail.confirmed_adult_count
     */
    private Integer confirmedAdultCount;

    /**
     * 已确认儿童数 ord_price_detail.confirmed_child_count
     */
    private Integer confirmedChildCount;

    /**
     * 核损成人数 ord_price_detail.loss_adult_count
     */
    private Integer lossAdultCount;

    /**
     * 核损儿童数 ord_price_detail.loss_child_count
     */
    private Integer lossChildCount;

    /**
     * 币种，0：人民币 ord_price_detail.currency_type
     */
    private String currencyType;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStockAdultCount() {
        return stockAdultCount;
    }

    public void setStockAdultCount(Integer stockAdultCount) {
        this.stockAdultCount = stockAdultCount;
    }

    public Integer getStockChildCount() {
        return stockChildCount;
    }

    public void setStockChildCount(Integer stockChildCount) {
        this.stockChildCount = stockChildCount;
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

    public Integer getConfirmedAdultCount() {
        return confirmedAdultCount;
    }

    public void setConfirmedAdultCount(Integer confirmedAdultCount) {
        this.confirmedAdultCount = confirmedAdultCount;
    }

    public Integer getConfirmedChildCount() {
        return confirmedChildCount;
    }

    public void setConfirmedChildCount(Integer confirmedChildCount) {
        this.confirmedChildCount = confirmedChildCount;
    }

    public Integer getLossAdultCount() {
        return lossAdultCount;
    }

    public void setLossAdultCount(Integer lossAdultCount) {
        this.lossAdultCount = lossAdultCount;
    }

    public Integer getLossChildCount() {
        return lossChildCount;
    }

    public void setLossChildCount(Integer lossChildCount) {
        this.lossChildCount = lossChildCount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

}