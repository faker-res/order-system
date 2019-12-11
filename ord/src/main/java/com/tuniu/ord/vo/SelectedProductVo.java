package com.tuniu.ord.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.tuniu.ord.jsondeseralize.JackJsonDateFormat;
import com.tuniu.ord.jsondeseralize.JackJsonDateParse;

public class SelectedProductVo {

    /**
     * 切位单号
     */
    private Integer dOrderId;
    
    private Integer productId;

    private String productName;
    
    private String productOwnerName;

    @JsonSerialize(using = JackJsonDateFormat.class)
    @JsonDeserialize(using = JackJsonDateParse.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonSerialize(using = JackJsonDateFormat.class)
    @JsonDeserialize(using = JackJsonDateParse.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

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
     * 已占位数量
     */
    private Integer occupiedNum;
    
    /**
     * 占位状态(0:未占位,1:占位失败,2:占位成功,3:确认成功,4:确认失败)
     */
    private Integer status = 0;

    /**
     * @return the dOrderId
     */
    public Integer getdOrderId() {
        return dOrderId;
    }

    /**
     * @param dOrderId the dOrderId to set
     */
    public void setdOrderId(Integer dOrderId) {
        this.dOrderId = dOrderId;
    }

    /**
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the adultPrice
     */
    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    /**
     * @param adultPrice the adultPrice to set
     */
    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    /**
     * @return the childPrice
     */
    public BigDecimal getChildPrice() {
        return childPrice;
    }

    /**
     * @param childPrice the childPrice to set
     */
    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    /**
     * @return the canSaleNum
     */
    public Integer getCanSaleNum() {
        return canSaleNum;
    }

    /**
     * @param canSaleNum the canSaleNum to set
     */
    public void setCanSaleNum(Integer canSaleNum) {
        this.canSaleNum = canSaleNum;
    }

    /**
     * @return the saledNum
     */
    public Integer getSaledNum() {
        return saledNum;
    }

    /**
     * @param saledNum the saledNum to set
     */
    public void setSaledNum(Integer saledNum) {
        this.saledNum = saledNum;
    }

    /**
     * @return the occupiedNum
     */
    public Integer getOccupiedNum() {
        return occupiedNum;
    }

    /**
     * @param occupiedNum the occupiedNum to set
     */
    public void setOccupiedNum(Integer occupiedNum) {
        this.occupiedNum = occupiedNum;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the productOwnerName
     */
    public String getProductOwnerName() {
        return productOwnerName;
    }

    /**
     * @param productOwnerName the productOwnerName to set
     */
    public void setProductOwnerName(String productOwnerName) {
        this.productOwnerName = productOwnerName;
    }
}
