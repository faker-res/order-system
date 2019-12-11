package com.tuniu.ord.vo;

import java.math.BigDecimal;

public class SalesInfoWhenCheckLossVo {
    private Integer dealOrderId;

    private Integer dealOrderType;

    private BigDecimal adultPrice;

    private BigDecimal childPrice;

    // ord_people_tourist
    private Integer peopleTouristId;

    // 0 成人 1 儿童
    private int touristType;

    // 外系统的游客编号
    private String touristId;

    public Integer getDealOrderId() {
        return dealOrderId;
    }

    public void setDealOrderId(Integer dealOrderId) {
        this.dealOrderId = dealOrderId;
    }

    public Integer getDealOrderType() {
        return dealOrderType;
    }

    public void setDealOrderType(Integer dealOrderType) {
        this.dealOrderType = dealOrderType;
    }

    public Integer getPeopleTouristId() {
        return peopleTouristId;
    }

    public void setPeopleTouristId(Integer peopleTouristId) {
        this.peopleTouristId = peopleTouristId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public int getTouristType() {
        return touristType;
    }

    public void setTouristType(int touristType) {
        this.touristType = touristType;
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

}
