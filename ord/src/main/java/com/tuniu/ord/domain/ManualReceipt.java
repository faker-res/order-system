package com.tuniu.ord.domain;

import java.math.BigDecimal;

public class ManualReceipt extends BaseManualDomain {

    /**
     * 
     */
    private static final long serialVersionUID = -7794912065395715122L;

    private String receiptNumber;

    private BigDecimal receiptPrice;

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber == null ? null : receiptNumber.trim();
    }

    public BigDecimal getReceiptPrice() {
        return receiptPrice;
    }

    public void setReceiptPrice(BigDecimal receiptPrice) {
        this.receiptPrice = receiptPrice;
    }

}