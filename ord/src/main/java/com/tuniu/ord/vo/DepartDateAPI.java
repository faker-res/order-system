/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.math.BigDecimal;

/**
 * @author zhairongping
 *
 */
public class DepartDateAPI {
    private Integer roundId;
    private Integer adultNum = new Integer(0);
    private Integer childNum = new Integer(0);
    private Integer babyNum = new Integer(0);
    private String date;
    private BigDecimal adultCost = new BigDecimal(0);
    private BigDecimal childCost = new BigDecimal(0);
    private BigDecimal babyCost = new BigDecimal(0);
    private Integer roomAddNum = new Integer(0);
    private BigDecimal roomAddPrice = new BigDecimal(0);

    /*
     * 切位单
     */
    private Integer extPurchaseId;

    /**
     * 库存类型 1-控位 2-FS 3-现询
     */
    private Integer stockType;

    /**
     * @return the extPurchaseId
     */
    public Integer getExtPurchaseId() {
        return extPurchaseId;
    }

    /**
     * @param extPurchaseId
     *            the extPurchaseId to set
     */
    public void setExtPurchaseId(Integer extPurchaseId) {
        this.extPurchaseId = extPurchaseId;
    }

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

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getAdultCost() {
        return adultCost;
    }

    public void setAdultCost(BigDecimal adultCost) {
        this.adultCost = adultCost;
    }

    public BigDecimal getChildCost() {
        return childCost;
    }

    public void setChildCost(BigDecimal childCost) {
        this.childCost = childCost;
    }

    public BigDecimal getBabyCost() {
        return babyCost;
    }

    public void setBabyCost(BigDecimal babyCost) {
        this.babyCost = babyCost;
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
