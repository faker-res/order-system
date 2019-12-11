package com.tuniu.ord.vo;

import java.math.BigDecimal;
import java.util.Date;

public class RealTimeAskResponse {
    /**
     * 途牛订单序列号
     */
    private String tuniuSerialId;
    /**
     * 途牛订单号
     */
    private String tuniuOrderId;

    /**
     * 切位单号
     */
    private Integer extPurchaseId;

    /**
     * 占位数量
     */
    private int reserveNum;
    /**
     * 占位保留时间
     */
    private Date reserveTime;
    /**
     * 占位成人价
     */
    private BigDecimal reserveAdultPrice;
    /**
     * 占位儿童价，如有儿童则必传
     */
    private BigDecimal reserveChildPrice;
    /**
     * 占位单房差
     */
    private BigDecimal reserveRoomAddPrice;
    /**
     * 价格币种 默认人民币 支持:1:欧元 2:美元 3:港币 4:新币 5:日元 6:韩元 7:加元 8:人民币 9:泰铢 10:英镑 11:澳元 12:马币 13:斐济元 14:瑞士法郎 15:澳门元
     */
    private String costCurrencyType;
    /**
     * 供应商订单编号
     */
    private String agencyOrderId;
    /**
     * 供应商产品编号
     */
    private String agencyProductId;

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

    public Integer getExtPurchaseId() {
        return extPurchaseId;
    }

    public void setExtPurchaseId(Integer extPurchaseId) {
        this.extPurchaseId = extPurchaseId;
    }

    public int getReserveNum() {
        return reserveNum;
    }

    public void setReserveNum(int reserveNum) {
        this.reserveNum = reserveNum;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public BigDecimal getReserveAdultPrice() {
        return reserveAdultPrice;
    }

    public void setReserveAdultPrice(BigDecimal reserveAdultPrice) {
        this.reserveAdultPrice = reserveAdultPrice;
    }

    public BigDecimal getReserveChildPrice() {
        return reserveChildPrice;
    }

    public void setReserveChildPrice(BigDecimal reserveChildPrice) {
        this.reserveChildPrice = reserveChildPrice;
    }

    public BigDecimal getReserveRoomAddPrice() {
        return reserveRoomAddPrice;
    }

    public void setReserveRoomAddPrice(BigDecimal reserveRoomAddPrice) {
        this.reserveRoomAddPrice = reserveRoomAddPrice;
    }

    public String getCostCurrencyType() {
        return costCurrencyType;
    }

    public void setCostCurrencyType(String costCurrencyType) {
        this.costCurrencyType = costCurrencyType;
    }

    public String getAgencyOrderId() {
        return agencyOrderId;
    }

    public void setAgencyOrderId(String agencyOrderId) {
        this.agencyOrderId = agencyOrderId;
    }

    public String getAgencyProductId() {
        return agencyProductId;
    }

    public void setAgencyProductId(String agencyProductId) {
        this.agencyProductId = agencyProductId;
    }

}
