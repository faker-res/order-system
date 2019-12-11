package com.tuniu.ord.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ManualSupplyment extends BaseManualDomain{

    /**
     * 
     */
    private static final long serialVersionUID = 6527993977956089332L;

    private Integer supplyType;

    private String supplyDesc;

    private Integer number;

    private BigDecimal price;

    public Integer getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(Integer supplyType) {
        this.supplyType = supplyType;
    }

    public String getSupplyDesc() {
        return supplyDesc;
    }

    public void setSupplyDesc(String supplyDesc) {
        this.supplyDesc = supplyDesc == null ? null : supplyDesc.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}