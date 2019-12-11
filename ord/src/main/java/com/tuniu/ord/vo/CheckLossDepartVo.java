package com.tuniu.ord.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class CheckLossDepartVo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 3372685095875511169L;

    /**
     * 团期（出游日期）
     */
    private String date;

    /**
     * 核损成人数
     */
    private Integer lossAdultNum;

    /**
     * 核损儿童数
     */
    private Integer lossChildNum;

    /**
     * 核损婴儿数
     */
    private Integer lossBabyNum;

    /**
     * 批次号
     */
    private Integer roundId;

    /**
     * 切位订单号
     */
    private Integer extPurchaseId;

    /**
     * 成人数
     */
    private Integer adultNum;

    /**
     * 儿童数
     */
    private Integer childNum;

    /**
     * 婴儿数
     */
    private Integer babyNum;

    /**
     * 对客成人核损金额
     */
    private BigDecimal lossAdultCustomer;

    /**
     * 对客儿童核损金额
     */
    private BigDecimal lossChildCustomer;

    /**
     * 对客婴儿核损金额
     */
    private BigDecimal lossBabyCustomer;

    /**
     * 对供应商成人核损金额
     */
    private BigDecimal lossAdultAgency;

    /**
     * 对供应商儿童核损金额
     */
    private BigDecimal lossChildAgency;

    /**
     * 对供应商婴儿核损金额
     */
    private BigDecimal lossBabyAgency;

    /**
     * 库存编号
     */
    private Integer stockType;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Integer getLossBabyNum() {
        return lossBabyNum;
    }

    public void setLossBabyNum(Integer lossBabyNum) {
        this.lossBabyNum = lossBabyNum;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getExtPurchaseId() {
        return extPurchaseId;
    }

    public void setExtPurchaseId(Integer extPurchaseId) {
        this.extPurchaseId = extPurchaseId;
    }

    public Integer getAdultNum() {
        return adultNum;
    }

    public void setAdultNum(Integer adultNum) {
        this.adultNum = adultNum;
    }

    public Integer getChildNum() {
        return childNum;
    }

    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    public Integer getBabyNum() {
        return babyNum;
    }

    public void setBabyNum(Integer babyNum) {
        this.babyNum = babyNum;
    }

    public BigDecimal getLossAdultCustomer() {
        return lossAdultCustomer;
    }

    public void setLossAdultCustomer(BigDecimal lossAdultCustomer) {
        this.lossAdultCustomer = lossAdultCustomer;
    }

    public BigDecimal getLossChildCustomer() {
        return lossChildCustomer;
    }

    public void setLossChildCustomer(BigDecimal lossChildCustomer) {
        this.lossChildCustomer = lossChildCustomer;
    }

    public BigDecimal getLossBabyCustomer() {
        return lossBabyCustomer;
    }

    public void setLossBabyCustomer(BigDecimal lossBabyCustomer) {
        this.lossBabyCustomer = lossBabyCustomer;
    }

    public BigDecimal getLossAdultAgency() {
        return lossAdultAgency;
    }

    public void setLossAdultAgency(BigDecimal lossAdultAgency) {
        this.lossAdultAgency = lossAdultAgency;
    }

    public BigDecimal getLossChildAgency() {
        return lossChildAgency;
    }

    public void setLossChildAgency(BigDecimal lossChildAgency) {
        this.lossChildAgency = lossChildAgency;
    }

    public BigDecimal getLossBabyAgency() {
        return lossBabyAgency;
    }

    public void setLossBabyAgency(BigDecimal lossBabyAgency) {
        this.lossBabyAgency = lossBabyAgency;
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

}
