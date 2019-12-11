package com.tuniu.ord.vo;

import java.math.BigDecimal;

public class ManualOrderPriceVo {
    /**
     * 订单价格
     */
    private BigDecimal orderPrice = BigDecimal.ZERO;
    /**
     * 剩余的应收账款
     */
    private BigDecimal receivablesMoney = BigDecimal.ZERO;

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getReceivablesMoney() {
        return receivablesMoney;
    }

    public void setReceivablesMoney(BigDecimal receivablesMoney) {
        this.receivablesMoney = receivablesMoney;
    }

}
