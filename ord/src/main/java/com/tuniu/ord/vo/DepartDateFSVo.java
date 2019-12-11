/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年4月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.math.BigDecimal;

/**
 * @author zhairongping
 *
 */
public class DepartDateFSVo {
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
     * 外币成人价
     */
    private BigDecimal adultCost;
    /**
     * 外币儿童价
     */
    private BigDecimal childCost;
    /**
     * 外币婴儿价
     */
    private BigDecimal babyCost;
    /**
     * 本币成人价
     */
    private BigDecimal adultCostRMB;
    /**
     * 本币儿童价
     */
    private BigDecimal childCostRMB;
    /**
     * 本币婴儿价
     */
    private BigDecimal babyCostRMB;
    /**
     * 团期
     */
    private String departDate;

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
     * @return the babyNum
     */
    public Integer getBabyNum() {
        return babyNum;
    }

    /**
     * @param babyNum
     *            the babyNum to set
     */
    public void setBabyNum(Integer babyNum) {
        this.babyNum = babyNum;
    }

    /**
     * @return the adultCost
     */
    public BigDecimal getAdultCost() {
        return adultCost;
    }

    /**
     * @param adultCost
     *            the adultCost to set
     */
    public void setAdultCost(BigDecimal adultCost) {
        this.adultCost = adultCost;
    }

    /**
     * @return the childCost
     */
    public BigDecimal getChildCost() {
        return childCost;
    }

    /**
     * @param childCost
     *            the childCost to set
     */
    public void setChildCost(BigDecimal childCost) {
        this.childCost = childCost;
    }

    /**
     * @return the babyCost
     */
    public BigDecimal getBabyCost() {
        return babyCost;
    }

    /**
     * @param babyCost
     *            the babyCost to set
     */
    public void setBabyCost(BigDecimal babyCost) {
        this.babyCost = babyCost;
    }

    /**
     * @return the adultCostRMB
     */
    public BigDecimal getAdultCostRMB() {
        return adultCostRMB;
    }

    /**
     * @param adultCostRMB
     *            the adultCostRMB to set
     */
    public void setAdultCostRMB(BigDecimal adultCostRMB) {
        this.adultCostRMB = adultCostRMB;
    }

    /**
     * @return the childCostRMB
     */
    public BigDecimal getChildCostRMB() {
        return childCostRMB;
    }

    /**
     * @param childCostRMB
     *            the childCostRMB to set
     */
    public void setChildCostRMB(BigDecimal childCostRMB) {
        this.childCostRMB = childCostRMB;
    }

    /**
     * @return the babyCostRMB
     */
    public BigDecimal getBabyCostRMB() {
        return babyCostRMB;
    }

    /**
     * @param babyCostRMB
     *            the babyCostRMB to set
     */
    public void setBabyCostRMB(BigDecimal babyCostRMB) {
        this.babyCostRMB = babyCostRMB;
    }

    /**
     * @return the departDate
     */
    public String getDepartDate() {
        return departDate;
    }

    /**
     * @param departDate
     *            the departDate to set
     */
    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

}
