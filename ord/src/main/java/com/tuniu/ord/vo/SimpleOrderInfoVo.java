/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月2日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 【执行单个数据迁移任务】
 * 
 * @author zhairongping
 *
 */
public class SimpleOrderInfoVo {
    // D类产品ID
    private Integer dProductId;
    // 本确认单成人数
    private Integer affirmAdultNum;
    // 确认单成人价格
    private BigDecimal affirmAdultPrice;
    /// 本确认单儿童数
    private Integer affirmChildNum;
    // 确认单儿童价格
    private BigDecimal affirmChildPrice;
    // 本确认单婴儿数
    private Integer affirmBabyNum;
    // 确认单婴儿价
    private BigDecimal affirmBabyPrice;
    // 单房差数量
    private Integer roomAddNum;
    // 单房差价格
    private BigDecimal roomAddPrice;
    /// 出游日期
    private String date;
    // 价格币种
    private String costCurrencyType;
    // 资源ＩＤ
    private Integer resourceId;
    // D类切位单
    private Integer dOrderId;
    // 需求id
    private Integer requirementId;
    // 途牛订单号
    private Integer tuniuOrderId;
    // 途牛产品编号
    private Integer aProductId;
    // 途牛产品名称
    private String aProductName;
    // 团模板ID
    private Integer tourBasicId;
    // 渠道号
    private String channelId;
    //
    private List<Tourist> touristInfoList = new ArrayList<Tourist>();

    public Integer getdProductId() {
        return dProductId;
    }

    public void setdProductId(Integer dProductId) {
        this.dProductId = dProductId;
    }

    public Integer getAffirmAdultNum() {
        return affirmAdultNum;
    }

    public void setAffirmAdultNum(Integer affirmAdultNum) {
        this.affirmAdultNum = affirmAdultNum;
    }

    public BigDecimal getAffirmAdultPrice() {
        return affirmAdultPrice;
    }

    public void setAffirmAdultPrice(BigDecimal affirmAdultPrice) {
        this.affirmAdultPrice = affirmAdultPrice;
    }

    public Integer getAffirmChildNum() {
        return affirmChildNum;
    }

    public void setAffirmChildNum(Integer affirmChildNum) {
        this.affirmChildNum = affirmChildNum;
    }

    public BigDecimal getAffirmChildPrice() {
        return affirmChildPrice;
    }

    public void setAffirmChildPrice(BigDecimal affirmChildPrice) {
        this.affirmChildPrice = affirmChildPrice;
    }

    public Integer getAffirmBabyNum() {
        return affirmBabyNum;
    }

    public void setAffirmBabyNum(Integer affirmBabyNum) {
        this.affirmBabyNum = affirmBabyNum;
    }

    public BigDecimal getAffirmBabyPrice() {
        return affirmBabyPrice;
    }

    public void setAffirmBabyPrice(BigDecimal affirmBabyPrice) {
        this.affirmBabyPrice = affirmBabyPrice;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCostCurrencyType() {
        return costCurrencyType;
    }

    public void setCostCurrencyType(String costCurrencyType) {
        this.costCurrencyType = costCurrencyType;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getdOrderId() {
        return dOrderId;
    }

    public void setdOrderId(Integer dOrderId) {
        this.dOrderId = dOrderId;
    }

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public Integer getTuniuOrderId() {
        return tuniuOrderId;
    }

    public void setTuniuOrderId(Integer tuniuOrderId) {
        this.tuniuOrderId = tuniuOrderId;
    }

    public Integer getaProductId() {
        return aProductId;
    }

    public void setaProductId(Integer aProductId) {
        this.aProductId = aProductId;
    }

    public String getaProductName() {
        return aProductName;
    }

    public void setaProductName(String aProductName) {
        this.aProductName = aProductName;
    }

    public Integer getTourBasicId() {
        return tourBasicId;
    }

    public void setTourBasicId(Integer tourBasicId) {
        this.tourBasicId = tourBasicId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public List<Tourist> getTouristInfoList() {
        return touristInfoList;
    }

    public void setTouristInfoList(List<Tourist> touristInfoList) {
        this.touristInfoList = touristInfoList;
    }

}
