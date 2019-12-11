package com.tuniu.ord.vo;

import java.math.BigDecimal;

public class RealTimeAskStockVo {
    private String tuniuSerialId;
    private String tuniuOrderId;
    private int type;
    // 供应商资源ID 即产品系统的团模板ID
    private String agencyProductId;
    private Integer extPurchaseId;
    private String groupNum;
    private String date;
    private String scheme;
    private int adultNum;
    private int childNum;
    private int babyNum;
    private int costCurrencyType;
    private BigDecimal adultPrice;
    private BigDecimal childPrice;
    private BigDecimal babyPrice;
    private int roomAddNum;
    private BigDecimal roomAddPrice;
    private int stockNum;
    /**
     * 先写死channelId=1
     */
    private int channelId = 1;
    /**
     * 途牛A
     */
    private String channelName = "途牛A";

    public String getTuniuSerialId() {
        return tuniuSerialId;
    }

    public void setTuniuSerialId(String tuniuSerialId) {
        this.tuniuSerialId = tuniuSerialId;
    }

    public String getTuniuOrderId() {
        return tuniuOrderId;
    }

    public void setTuniuOrderId(String tuniuOrderId) {
        this.tuniuOrderId = tuniuOrderId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAgencyProductId() {
        return agencyProductId;
    }

    public void setAgencyProductId(String agencyProductId) {
        this.agencyProductId = agencyProductId;
    }

    public Integer getExtPurchaseId() {
        return extPurchaseId;
    }

    public void setExtPurchaseId(Integer extPurchaseId) {
        this.extPurchaseId = extPurchaseId;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public int getAdultNum() {
        return adultNum;
    }

    public void setAdultNum(int adultNum) {
        this.adultNum = adultNum;
    }

    public int getChildNum() {
        return childNum;
    }

    public void setChildNum(int childNum) {
        this.childNum = childNum;
    }

    public int getBabyNum() {
        return babyNum;
    }

    public void setBabyNum(int babyNum) {
        this.babyNum = babyNum;
    }

    public int getCostCurrencyType() {
        return costCurrencyType;
    }

    public void setCostCurrencyType(int costCurrencyType) {
        this.costCurrencyType = costCurrencyType;
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

    public BigDecimal getBabyPrice() {
        return babyPrice;
    }

    public void setBabyPrice(BigDecimal babyPrice) {
        this.babyPrice = babyPrice;
    }

    public int getRoomAddNum() {
        return roomAddNum;
    }

    public void setRoomAddNum(int roomAddNum) {
        this.roomAddNum = roomAddNum;
    }

    public BigDecimal getRoomAddPrice() {
        return roomAddPrice;
    }

    public void setRoomAddPrice(BigDecimal roomAddPrice) {
        this.roomAddPrice = roomAddPrice;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

}
