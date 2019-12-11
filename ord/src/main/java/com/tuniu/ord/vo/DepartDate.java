/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.math.BigDecimal;

/**
 * @author zhairongping
 *
 */
public class DepartDate {
    private Integer totalAdultNum;
    private Integer affirmAdultNum = new Integer(0);
    private BigDecimal affirmAdultPrice;
    private Integer totalChildNum;
    private Integer affirmChildNum = new Integer(0);
    private BigDecimal affirmChildPrice;
    private Integer totalBabyNum;
    private Integer affirmBabyNum = new Integer(0);
    private BigDecimal affirmBabyPrice;
    private String date;
    private Integer costCurrencyType;
    private Integer roomAddNum;
    private BigDecimal roomAddPrice;
    private Integer roomAddPriceOption;
    private String roundId;
    // Integer更改为Long
    private Long resourceId;

    /*
     * 切位单
     */
    private Integer extPurchaseId;

    /**
     * 库存类型 1-控位 2-FS 3-现询
     */
    private Integer stockType;

    /**
     * @return the stockType
     */
    public Integer getStockType() {
        return stockType;
    }

    /**
     * @param stockType
     *            the stockType to set
     */
    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getTotalAdultNum() {
        return totalAdultNum;
    }

    public void setTotalAdultNum(Integer totalAdultNum) {
        this.totalAdultNum = totalAdultNum;
    }

    public Integer getAffirmAdultNum() {
        return affirmAdultNum;
    }

    public void setAffirmAdultNum(Integer affirmAdultNum) {
        this.affirmAdultNum = affirmAdultNum;
    }

    public Integer getTotalChildNum() {
        return totalChildNum;
    }

    public void setTotalChildNum(Integer totalChildNum) {
        this.totalChildNum = totalChildNum;
    }

    public Integer getAffirmChildNum() {
        return affirmChildNum;
    }

    public void setAffirmChildNum(Integer affirmChildNum) {
        this.affirmChildNum = affirmChildNum;
    }

    public Integer getTotalBabyNum() {
        return totalBabyNum;
    }

    public void setTotalBabyNum(Integer totalBabyNum) {
        this.totalBabyNum = totalBabyNum;
    }

    public Integer getAffirmBabyNum() {
        return affirmBabyNum;
    }

    public void setAffirmBabyNum(Integer affirmBabyNum) {
        this.affirmBabyNum = affirmBabyNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCostCurrencyType() {
        return costCurrencyType;
    }

    public void setCostCurrencyType(Integer costCurrencyType) {
        this.costCurrencyType = costCurrencyType;
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

    public Integer getRoomAddPriceOption() {
        return roomAddPriceOption;
    }

    public void setRoomAddPriceOption(Integer roomAddPriceOption) {
        this.roomAddPriceOption = roomAddPriceOption;
    }

    public String getRoundId() {
        return roundId;
    }

    public void setRoundId(String roundId) {
        this.roundId = roundId;
    }

    public BigDecimal getAffirmAdultPrice() {
        return affirmAdultPrice;
    }

    public void setAffirmAdultPrice(BigDecimal affirmAdultPrice) {
        this.affirmAdultPrice = affirmAdultPrice;
    }

    public BigDecimal getAffirmChildPrice() {
        return affirmChildPrice;
    }

    public void setAffirmChildPrice(BigDecimal affirmChildPrice) {
        this.affirmChildPrice = affirmChildPrice;
    }

    public BigDecimal getAffirmBabyPrice() {
        return affirmBabyPrice;
    }

    public void setAffirmBabyPrice(BigDecimal affirmBabyPrice) {
        this.affirmBabyPrice = affirmBabyPrice;
    }

    public Integer getExtPurchaseId() {
        return extPurchaseId;
    }

    public void setExtPurchaseId(Integer extPurchaseId) {
        this.extPurchaseId = extPurchaseId;
    }

}
