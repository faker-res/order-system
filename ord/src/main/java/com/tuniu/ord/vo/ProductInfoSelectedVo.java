package com.tuniu.ord.vo;

import java.math.BigDecimal;

public class ProductInfoSelectedVo {
    private Integer manualOccpuyId;
    private Integer productId;
    private String productName;
    private String tourDate;
    private String deadLineDate;
    private BigDecimal adultPrice;
    private BigDecimal childPrice;
    private Integer occupyNum;
    private String productOwner;
    /**
     * 0 占位成功 1 占位失败 3 确认成功 4 确认失败
     */
    private Integer occupyStatus;

    public Integer getManualOccpuyId() {
        return manualOccpuyId;
    }

    public void setManualOccpuyId(Integer manualOccpuyId) {
        this.manualOccpuyId = manualOccpuyId;
    }

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

    public String getTourDate() {
        return tourDate;
    }

    public void setTourDate(String tourDate) {
        this.tourDate = tourDate;
    }

    public String getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(String deadLineDate) {
        this.deadLineDate = deadLineDate;
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

    public Integer getOccupyNum() {
        return occupyNum;
    }

    public void setOccupyNum(Integer occupyNum) {
        this.occupyNum = occupyNum;
    }

    public String getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(String productOwner) {
        this.productOwner = productOwner;
    }

    public Integer getOccupyStatus() {
        return occupyStatus;
    }

    public void setOccupyStatus(Integer occupyStatus) {
        this.occupyStatus = occupyStatus;
    }

}
