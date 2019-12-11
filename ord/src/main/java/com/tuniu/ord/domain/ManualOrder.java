package com.tuniu.ord.domain;

import java.math.BigDecimal;

public class ManualOrder extends BaseDomain {
    /**
     * 
     */
    private static final long serialVersionUID = -8911183227527356460L;

    private Integer statusCode;

    private BigDecimal adultPrice;

    private Integer adultCount;

    private BigDecimal childPrice;

    private Integer childCount;

    private BigDecimal singleRoomPrice;

    private Integer singleRoomCount;

    private BigDecimal supplementItem;

    private BigDecimal totalPrice;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode == null ? null : statusCode;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public BigDecimal getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public BigDecimal getSingleRoomPrice() {
        return singleRoomPrice;
    }

    public void setSingleRoomPrice(BigDecimal singleRoomPrice) {
        this.singleRoomPrice = singleRoomPrice;
    }

    public Integer getSingleRoomCount() {
        return singleRoomCount;
    }

    public void setSingleRoomCount(Integer singleRoomCount) {
        this.singleRoomCount = singleRoomCount;
    }

    public BigDecimal getSupplementItem() {
        return supplementItem;
    }

    public void setSupplementItem(BigDecimal supplementItem) {
        this.supplementItem = supplementItem;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}