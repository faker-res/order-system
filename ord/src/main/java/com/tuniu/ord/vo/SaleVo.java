/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月15日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhairongping
 * 
 */
public class SaleVo {
    private Integer orderId;
    private Integer tourBasicId;
    private String startDate;
    private String backDate;
    // 渠道 分销 1 自营 2 代理 3
    private String channelId;
    /**
     * 外部订单号
     */
    private String channelOrderId;
    private Integer adultNum;
    private Integer childNum;
    private Integer addNum;
    private BigDecimal adultCost;
    private BigDecimal childCost;
    private BigDecimal addPrice;
    private BigDecimal totalCost;
    private String currencyType;
    private String specialRemark;
    /**
     * 备注
     */
    private String remark;
    /**
     * 订单增补备注
     */
    private String supplyRemark;
    /**
     * 自理资源信息
     */
    private String otherResources;
    /**
     * 订单备注
     */
    private String orderRemark;

    private List<ConsumerVo> consumers;
    private Integer upgradeId = new Integer(0);
    private Integer productLineId;

    // 会员ID
    private Integer memberId;
    // 会员名称
    private String memberName;

    /**
     * 迁移标签 0：非迁移 1：迁移
     */
    private Integer migrateFlag = new Integer(0);

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTourBasicId() {
        return tourBasicId;
    }

    public void setTourBasicId(Integer tourBasicId) {
        this.tourBasicId = tourBasicId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelOrderId() {
        return channelOrderId;
    }

    public void setChannelOrderId(String channelOrderId) {
        this.channelOrderId = channelOrderId;
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

    public Integer getAddNum() {
        return addNum;
    }

    public void setAddNum(Integer addNum) {
        this.addNum = addNum;
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

    public BigDecimal getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(BigDecimal addPrice) {
        this.addPrice = addPrice;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getSpecialRemark() {
        return specialRemark;
    }

    public void setSpecialRemark(String specialRemark) {
        this.specialRemark = specialRemark;
    }

    public List<ConsumerVo> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<ConsumerVo> consumers) {
        this.consumers = consumers;
    }

    public Integer getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(Integer upgradeId) {
        this.upgradeId = upgradeId;
    }

    public Integer getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(Integer productLineId) {
        this.productLineId = productLineId;
    }

    public Integer getMigrateFlag() {
        return migrateFlag;
    }

    public void setMigrateFlag(Integer migrateFlag) {
        this.migrateFlag = migrateFlag;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the supplyRemark
     */
    public String getSupplyRemark() {
        return supplyRemark;
    }

    /**
     * @param supplyRemark
     *            the supplyRemark to set
     */
    public void setSupplyRemark(String supplyRemark) {
        this.supplyRemark = supplyRemark;
    }

    /**
     * @return the otherResources
     */
    public String getOtherResources() {
        return otherResources;
    }

    /**
     * @param otherResources
     *            the otherResources to set
     */
    public void setOtherResources(String otherResources) {
        this.otherResources = otherResources;
    }

    /**
     * @return the orderRemark
     */
    public String getOrderRemark() {
        return orderRemark;
    }

    /**
     * @param orderRemark
     *            the orderRemark to set
     */
    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

}
