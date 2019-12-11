package com.tuniu.ord.vo;

import java.math.BigDecimal;

public class CheckLossDiffPriceVo {
    private Integer dealOrderId;
    private Integer channelDealOrderId;
    private BigDecimal lossAdultCustomer;
    private BigDecimal lossChildCustomer;

    public Integer getDealOrderId() {
        return dealOrderId;
    }

    public void setDealOrderId(Integer dealOrderId) {
        this.dealOrderId = dealOrderId;
    }

    public Integer getChannelDealOrderId() {
        return channelDealOrderId;
    }

    public void setChannelDealOrderId(Integer channelDealOrderId) {
        this.channelDealOrderId = channelDealOrderId;
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

}
