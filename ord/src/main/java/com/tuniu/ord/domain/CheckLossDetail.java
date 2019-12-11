/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Wed May 25 09:56:13 CST 2016
 * Description:
 */
package com.tuniu.ord.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * CheckLossDetail check_loss_detail
 */
public class CheckLossDetail extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 核损id check_loss_detail.check_loss_id
     */
    private Integer checkLossId;

    /**
     * 批次号 check_loss_detail.stockType
     */
    private Integer stockType;

    /**
     * 批次号 check_loss_detail.batch_number
     */
    private Integer batchNumber;

    // 切位单号
    private Integer dealOrderId;

    /**
     * 变更前成人数量 check_loss_detail.adult_count_before
     */
    private Integer adultCountBefore;

    /**
     * 变更前儿童数量 check_loss_detail.child_count_before
     */
    private Integer childCountBefore;

    /**
     * 变更前婴儿数量 check_loss_detail.baby_count_before
     */
    private Integer babyCountBefore;

    /**
     * 变更后成人数量 check_loss_detail.adult_count_after
     */
    private Integer adultCountAfter;

    /**
     * 变更后儿童数量 check_loss_detail.child_count_after
     */
    private Integer childCountAfter;

    /**
     * 变更后婴儿数量 check_loss_detail.baby_count_after
     */
    private Integer babyCountAfter;

    /**
     * 每单位成人损失金额（对客） check_loss_detail.loss_adult_customer
     */
    private BigDecimal lossAdultCustomer;

    /**
     * 每单位儿童损失金额（对客） check_loss_detail.loss_child_customer
     */
    private BigDecimal lossChildCustomer;

    /**
     * 每单位婴儿损失金额（对客） check_loss_detail.loss_baby_customer
     */
    private BigDecimal lossBabyCustomer;

    /**
     * 是否可再售卖，0:不可以；1:可以 check_loss_detail.resell_flag
     */
    private Byte resellFlag;

    /**
     * 核损方案有效期 check_loss_detail.expire_time
     */
    private Date expireTime;

    /**
     * 核损备注 check_loss_detail.remark
     */
    private String remark;

    public Integer getCheckLossId() {
        return checkLossId;
    }

    public void setCheckLossId(Integer checkLossId) {
        this.checkLossId = checkLossId;
    }

    public Integer getDealOrderId() {
        return dealOrderId;
    }

    public void setDealOrderId(Integer dealOrderId) {
        this.dealOrderId = dealOrderId;
    }

    public Integer getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(Integer batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Integer getAdultCountBefore() {
        return adultCountBefore;
    }

    public void setAdultCountBefore(Integer adultCountBefore) {
        this.adultCountBefore = adultCountBefore;
    }

    public Integer getChildCountBefore() {
        return childCountBefore;
    }

    public void setChildCountBefore(Integer childCountBefore) {
        this.childCountBefore = childCountBefore;
    }

    public Integer getBabyCountBefore() {
        return babyCountBefore;
    }

    public void setBabyCountBefore(Integer babyCountBefore) {
        this.babyCountBefore = babyCountBefore;
    }

    public Integer getAdultCountAfter() {
        return adultCountAfter;
    }

    public void setAdultCountAfter(Integer adultCountAfter) {
        this.adultCountAfter = adultCountAfter;
    }

    public Integer getChildCountAfter() {
        return childCountAfter;
    }

    public void setChildCountAfter(Integer childCountAfter) {
        this.childCountAfter = childCountAfter;
    }

    public Integer getBabyCountAfter() {
        return babyCountAfter;
    }

    public void setBabyCountAfter(Integer babyCountAfter) {
        this.babyCountAfter = babyCountAfter;
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

    public Byte getResellFlag() {
        return resellFlag;
    }

    public void setResellFlag(Byte resellFlag) {
        this.resellFlag = resellFlag;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

}