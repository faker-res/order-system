package com.tuniu.ord.vo;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.tuniu.ord.common.util.JackJsonDateFormat;
import com.tuniu.ord.jsondeseralize.JackJsonDateParse;
import com.tuniu.ord.vo.common.BaseVo;

public class ManualRequirementVo extends BaseVo {

    private Integer manualOrderId;

    private Integer productId;

    private String productName;

    @JsonSerialize(using = JackJsonDateFormat.class)
    @JsonDeserialize(using = JackJsonDateParse.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonSerialize(using = JackJsonDateFormat.class)
    @JsonDeserialize(using = JackJsonDateParse.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Integer days;

    private Integer destCategoryId;

    private String destCategoryName;

    private Integer firstDestGroupId;

    private String firstDestGroupName;

    private Integer secDestGroupId;

    private String secDestGroupName;
    
    private Integer adultNum;
    
    private Integer childNum;
    
    private Integer olderNum;

    /**
     * @return the manualOrderId
     */
    public Integer getManualOrderId() {
        return manualOrderId;
    }

    /**
     * @param manualOrderId the manualOrderId to set
     */
    public void setManualOrderId(Integer manualOrderId) {
        this.manualOrderId = manualOrderId;
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
     * @return the days
     */
    public Integer getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    /**
     * @return the destCategoryId
     */
    public Integer getDestCategoryId() {
        return destCategoryId;
    }

    /**
     * @param destCategoryId the destCategoryId to set
     */
    public void setDestCategoryId(Integer destCategoryId) {
        this.destCategoryId = destCategoryId;
    }

    /**
     * @return the destCategoryName
     */
    public String getDestCategoryName() {
        return destCategoryName;
    }

    /**
     * @param destCategoryName the destCategoryName to set
     */
    public void setDestCategoryName(String destCategoryName) {
        this.destCategoryName = destCategoryName;
    }

    /**
     * @return the firstDestGroupId
     */
    public Integer getFirstDestGroupId() {
        return firstDestGroupId;
    }

    /**
     * @param firstDestGroupId the firstDestGroupId to set
     */
    public void setFirstDestGroupId(Integer firstDestGroupId) {
        this.firstDestGroupId = firstDestGroupId;
    }

    /**
     * @return the firstDestGroupName
     */
    public String getFirstDestGroupName() {
        return firstDestGroupName;
    }

    /**
     * @param firstDestGroupName the firstDestGroupName to set
     */
    public void setFirstDestGroupName(String firstDestGroupName) {
        this.firstDestGroupName = firstDestGroupName;
    }

    /**
     * @return the secDestGroupId
     */
    public Integer getSecDestGroupId() {
        return secDestGroupId;
    }

    /**
     * @param secDestGroupId the secDestGroupId to set
     */
    public void setSecDestGroupId(Integer secDestGroupId) {
        this.secDestGroupId = secDestGroupId;
    }

    /**
     * @return the secDestGroupName
     */
    public String getSecDestGroupName() {
        return secDestGroupName;
    }

    /**
     * @param secDestGroupName the secDestGroupName to set
     */
    public void setSecDestGroupName(String secDestGroupName) {
        this.secDestGroupName = secDestGroupName;
    }

    /**
     * @return the adultNum
     */
    public Integer getAdultNum() {
        return adultNum;
    }

    /**
     * @param adultNum the adultNum to set
     */
    public void setAdultNum(Integer adultNum) {
        this.adultNum = adultNum;
    }

    /**
     * @return the childNum
     */
    public Integer getChildNum() {
        return childNum;
    }

    /**
     * @param childNum the childNum to set
     */
    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    /**
     * @return the olderNum
     */
    public Integer getOlderNum() {
        return olderNum;
    }

    /**
     * @param olderNum the olderNum to set
     */
    public void setOlderNum(Integer olderNum) {
        this.olderNum = olderNum;
    }
}
