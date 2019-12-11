package com.tuniu.ord.domain;

import java.math.BigDecimal;

public class CheckLossDiffInfo extends BaseDomain {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer checkLossId;

    private String channelOrderId;

    private Integer requirementId;

    private String touristId;

    private Integer dealOrderId;

    private BigDecimal adultPrice;

    private BigDecimal childPrice;

    private Integer channelDealOrderId;

    private Integer lossAdultNum;

    private Integer lossChildNum;

    private BigDecimal channelAdultPrice;

    private BigDecimal channelChildPrice;

    public Integer getCheckLossId() {
        return checkLossId;
    }

    public void setCheckLossId(Integer checkLossId) {
        this.checkLossId = checkLossId;
    }

    public String getChannelOrderId() {
        return channelOrderId;
    }

    public void setChannelOrderId(String channelOrderId) {
        this.channelOrderId = channelOrderId;
    }

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public Integer getDealOrderId() {
        return dealOrderId;
    }

    public void setDealOrderId(Integer dealOrderId) {
        this.dealOrderId = dealOrderId;
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

    public Integer getChannelDealOrderId() {
        return channelDealOrderId;
    }

    public void setChannelDealOrderId(Integer channelDealOrderId) {
        this.channelDealOrderId = channelDealOrderId;
    }

    public Integer getLossAdultNum() {
        return lossAdultNum;
    }

    public void setLossAdultNum(Integer lossAdultNum) {
        this.lossAdultNum = lossAdultNum;
    }

    public Integer getLossChildNum() {
        return lossChildNum;
    }

    public void setLossChildNum(Integer lossChildNum) {
        this.lossChildNum = lossChildNum;
    }

    public BigDecimal getChannelAdultPrice() {
        return channelAdultPrice;
    }

    public void setChannelAdultPrice(BigDecimal channelAdultPrice) {
        this.channelAdultPrice = channelAdultPrice;
    }

    public BigDecimal getChannelChildPrice() {
        return channelChildPrice;
    }

    public void setChannelChildPrice(BigDecimal channelChildPrice) {
        this.channelChildPrice = channelChildPrice;
    }

}