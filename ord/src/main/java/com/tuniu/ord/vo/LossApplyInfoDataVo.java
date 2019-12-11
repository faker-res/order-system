/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhairongping
 * 
 */
public class LossApplyInfoDataVo {
    private Integer orderId;
    // 渠道号
    private Integer channelId;
    // 渠道订单号
    private String channelOrderId;
    private String currencyType;
    private Integer adultNum;
    private Integer childNum;
    private BigDecimal totalLoss;
    private Integer chargeUid;
    private String chargeUname;
    private List<Integer> consumerIds;
    private Integer isSaleAgain;
    private Integer checkMode;

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelOrderId() {
        return channelOrderId;
    }

    public void setChannelOrderId(String channelOrderId) {
        this.channelOrderId = channelOrderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
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

    public BigDecimal getTotalLoss() {
        return totalLoss;
    }

    public void setTotalLoss(BigDecimal totalLoss) {
        this.totalLoss = totalLoss;
    }

    public Integer getChargeUid() {
        return chargeUid;
    }

    public void setChargeUid(Integer chargeUid) {
        this.chargeUid = chargeUid;
    }

    public String getChargeUname() {
        return chargeUname;
    }

    public void setChargeUname(String chargeUname) {
        this.chargeUname = chargeUname;
    }

    public List<Integer> getConsumerIds() {
        return consumerIds;
    }

    public void setConsumerIds(List<Integer> consumerIds) {
        this.consumerIds = consumerIds;
    }

    public Integer getIsSaleAgain() {
        return isSaleAgain;
    }

    public void setIsSaleAgain(Integer isSaleAgain) {
        this.isSaleAgain = isSaleAgain;
    }

    public Integer getCheckMode() {
        return checkMode;
    }

    public void setCheckMode(Integer checkMode) {
        this.checkMode = checkMode;
    }
}
