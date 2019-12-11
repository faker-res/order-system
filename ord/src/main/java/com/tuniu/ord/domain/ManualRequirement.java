package com.tuniu.ord.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.tuniu.ord.common.util.JackJsonDateFormat;
import com.tuniu.ord.jsondeseralize.JackJsonDateParse;

public class ManualRequirement extends BaseManualDomain{
    /**
     * 
     */
    private static final long serialVersionUID = -7372604642863735510L;
    
    private Integer dOrderId;

    private Integer cutType;
    
    private Integer adultNum;

    private Integer childNum;

    private Integer olderNum;
    
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
    
    private Integer destId;
    
    private String destName;

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
     * @return the cutType
     */
    public Integer getCutType() {
        return cutType;
    }

    /**
     * @param cutType the cutType to set
     */
    public void setCutType(Integer cutType) {
        this.cutType = cutType;
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
        this.productName = productName == null ? null : productName.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getDestCategoryId() {
        return destCategoryId;
    }

    public void setDestCategoryId(Integer destCategoryId) {
        this.destCategoryId = destCategoryId;
    }

    public String getDestCategoryName() {
        return destCategoryName;
    }

    public void setDestCategoryName(String destCategoryName) {
        this.destCategoryName = destCategoryName == null ? null : destCategoryName.trim();
    }

    public Integer getFirstDestGroupId() {
        return firstDestGroupId;
    }

    public void setFirstDestGroupId(Integer firstDestGroupId) {
        this.firstDestGroupId = firstDestGroupId;
    }

    public String getFirstDestGroupName() {
        return firstDestGroupName;
    }

    public void setFirstDestGroupName(String firstDestGroupName) {
        this.firstDestGroupName = firstDestGroupName == null ? null : firstDestGroupName.trim();
    }

    public Integer getSecDestGroupId() {
        return secDestGroupId;
    }

    public void setSecDestGroupId(Integer secDestGroupId) {
        this.secDestGroupId = secDestGroupId;
    }

    public String getSecDestGroupName() {
        return secDestGroupName;
    }

    public void setSecDestGroupName(String secDestGroupName) {
        this.secDestGroupName = secDestGroupName == null ? null : secDestGroupName.trim();
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

    /**
     * @return the destId
     */
    public Integer getDestId() {
        return destId;
    }

    /**
     * @param destId the destId to set
     */
    public void setDestId(Integer destId) {
        this.destId = destId;
    }

    /**
     * @return the destName
     */
    public String getDestName() {
        return destName;
    }

    /**
     * @param destName the destName to set
     */
    public void setDestName(String destName) {
        this.destName = destName;
    }
}