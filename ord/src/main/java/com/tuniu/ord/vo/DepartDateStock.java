/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * 
 * @author zhairongping
 *
 */
public class DepartDateStock {
    private Integer outId;
    private List<Integer> outIds;
    private String departDate;
    private Integer adultNum;
    private Integer childNum;
    private Integer babyNum;
    private BigDecimal adultCost;
    private BigDecimal childCost;
    private BigDecimal babyCost;
    private BigDecimal adultCostRMB;
    private BigDecimal childCostRMB;
    private BigDecimal babyCostRMB;
    private Integer recovery;

    public List<Integer> getOutIds() {
        return outIds;
    }

    public void setOutIds(List<Integer> outIds) {
        this.outIds = outIds;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
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

    public BigDecimal getAdultCostRMB() {
        return adultCostRMB;
    }

    public void setAdultCostRMB(BigDecimal adultCostRMB) {
        this.adultCostRMB = adultCostRMB;
    }

    public BigDecimal getChildCostRMB() {
        return childCostRMB;
    }

    public void setChildCostRMB(BigDecimal childCostRMB) {
        this.childCostRMB = childCostRMB;
    }

    public BigDecimal getBabyCostRMB() {
        return babyCostRMB;
    }

    public void setBabyCostRMB(BigDecimal babyCostRMB) {
        this.babyCostRMB = babyCostRMB;
    }

    public Integer getRecovery() {
        return recovery;
    }

    public void setRecovery(Integer recovery) {
        this.recovery = recovery;
    }

}
