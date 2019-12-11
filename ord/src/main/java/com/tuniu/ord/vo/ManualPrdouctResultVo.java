package com.tuniu.ord.vo;

import java.math.BigDecimal;

public class ManualPrdouctResultVo {
    private Integer productId;

    private String productName;

    private String departDate;

    private String bookEndDate;
    /**
     * 产品负责人
     */
    private String productOwnerName;

    private Integer dOrderId;

    private BigDecimal adultPrice;

    private BigDecimal childPrice;

    /**
     * 可收
     */
    private Integer canSaleNum;

    /**
     * 已售
     */
    private Integer saledNum;

    /**
     * 占位数量
     */
    private Integer occupyNum;

    private boolean isStop;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getBookEndDate() {
        return bookEndDate;
    }

    public void setBookEndDate(String bookEndDate) {
        this.bookEndDate = bookEndDate;
    }

    public String getProductOwnerName() {
        return productOwnerName;
    }

    public void setProductOwnerName(String productOwnerName) {
        this.productOwnerName = productOwnerName;
    }

    public Integer getdOrderId() {
        return dOrderId;
    }

    public void setdOrderId(Integer dOrderId) {
        this.dOrderId = dOrderId;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BigDecimal getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    public Integer getCanSaleNum() {
        return canSaleNum;
    }

    public void setCanSaleNum(Integer canSaleNum) {
        this.canSaleNum = canSaleNum;
    }

    public Integer getSaledNum() {
        return saledNum;
    }

    public void setSaledNum(Integer saledNum) {
        this.saledNum = saledNum;
    }

    public Integer getOccupyNum() {
        return occupyNum;
    }

    public void setOccupyNum(Integer occupyNum) {
        this.occupyNum = occupyNum;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean isStop) {
        this.isStop = isStop;
    }

}
