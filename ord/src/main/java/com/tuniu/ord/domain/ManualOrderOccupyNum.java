/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.domain;

import java.math.BigDecimal;

/**
 * 人工订单占位数量
 * 
 * @author zhoujie8
 * 
 */
public class ManualOrderOccupyNum {
    private Integer dOrderId;
    private Integer adultNum;
    private Integer childNum;
    private BigDecimal adultPrice;
    private BigDecimal childPrice;
    private Integer delFlag;

    /**
     * @return the delFlag
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag
     *            the delFlag to set
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return the dOrderId
     */
    public Integer getdOrderId() {
        return dOrderId;
    }

    /**
     * @param dOrderId
     *            the dOrderId to set
     */
    public void setdOrderId(Integer dOrderId) {
        this.dOrderId = dOrderId;
    }

    /**
     * @return the adultNum
     */
    public Integer getAdultNum() {
        return adultNum;
    }

    /**
     * @param adultNum
     *            the adultNum to set
     */
    public void setAdultNum(Integer adultNum) {
        this.adultNum = adultNum;
    }

    /**
     * @return the childNum
     */
    public Integer getChildNum() {
        return childNum;
    }

    /**
     * @param childNum
     *            the childNum to set
     */
    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    /**
     * @return the adultPrice
     */
    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    /**
     * @param adultPrice
     *            the adultPrice to set
     */
    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    /**
     * @return the childPrice
     */
    public BigDecimal getChildPrice() {
        return childPrice;
    }

    /**
     * @param childPrice
     *            the childPrice to set
     */
    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

}
