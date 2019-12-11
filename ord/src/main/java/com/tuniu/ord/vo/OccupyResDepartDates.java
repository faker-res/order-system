/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-30                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.tuniu.ord.common.util.JackJsonDateTimeParse;

/**
 * <Description> <br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-5-30 <br>
 */

public class OccupyResDepartDates {

    /**
     * vendorId
     */
    private Integer vendorId;

    /**
     * vendorName
     */
    private String vendorName;

    /**
     * releaseTime
     */
    private Date releaseTime;

    /**
     * departDate
     */
    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    private Date departDate;

    /**
     * adultNum
     */
    private Integer adultNum;

    /**
     * costCurrencyType
     */
    private Integer costCurrencyType;

    /**
     * adultCost
     */
    private BigDecimal adultCost;

    /**
     * childCost
     */
    private BigDecimal childCost;

    /**
     * babyCost
     */
    private BigDecimal babyCost;

    /**
     * singleCost
     */
    private BigDecimal singleCost;

    /**
     * roundId 批次id
     */
    private Integer roundId;

    /**
     * outId 占位单id
     */
    private Integer outId;

    /**
     * get vendorId
     * 
     * @return Returns the vendorId.<br>
     */
    public Integer getVendorId() {
        return vendorId;
    }

    /**
     * set vendorId
     * 
     * @param vendorId
     *            The vendorId to set. <br>
     */
    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * get vendorName
     * 
     * @return Returns the vendorName.<br>
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * set vendorName
     * 
     * @param vendorName
     *            The vendorName to set. <br>
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * get releaseTime
     * 
     * @return Returns the releaseTime.<br>
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * set releaseTime
     * 
     * @param releaseTime
     *            The releaseTime to set. <br>
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * get departDate
     * 
     * @return Returns the departDate.<br>
     */
    public Date getDepartDate() {
        return departDate;
    }

    /**
     * set departDate
     * 
     * @param departDate
     *            The departDate to set. <br>
     */
    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    /**
     * get adultNum
     * 
     * @return Returns the adultNum.<br>
     */
    public Integer getAdultNum() {
        return adultNum;
    }

    /**
     * set adultNum
     * 
     * @param adultNum
     *            The adultNum to set. <br>
     */
    public void setAdultNum(Integer adultNum) {
        this.adultNum = adultNum;
    }

    /**
     * get costCurrencyType
     * 
     * @return Returns the costCurrencyType.<br>
     */
    public Integer getCostCurrencyType() {
        return costCurrencyType;
    }

    /**
     * set costCurrencyType
     * 
     * @param costCurrencyType
     *            The costCurrencyType to set. <br>
     */
    public void setCostCurrencyType(Integer costCurrencyType) {
        this.costCurrencyType = costCurrencyType;
    }

    /**
     * get adultCost
     * 
     * @return Returns the adultCost.<br>
     */
    public BigDecimal getAdultCost() {
        return adultCost;
    }

    /**
     * set adultCost
     * 
     * @param adultCost
     *            The adultCost to set. <br>
     */
    public void setAdultCost(BigDecimal adultCost) {
        this.adultCost = adultCost;
    }

    /**
     * get childCost
     * 
     * @return Returns the childCost.<br>
     */
    public BigDecimal getChildCost() {
        return childCost;
    }

    /**
     * set childCost
     * 
     * @param childCost
     *            The childCost to set. <br>
     */
    public void setChildCost(BigDecimal childCost) {
        this.childCost = childCost;
    }

    /**
     * get babyCost
     * 
     * @return Returns the babyCost.<br>
     */
    public BigDecimal getBabyCost() {
        return babyCost;
    }

    /**
     * set babyCost
     * 
     * @param babyCost
     *            The babyCost to set. <br>
     */
    public void setBabyCost(BigDecimal babyCost) {
        this.babyCost = babyCost;
    }

    /**
     * get singleCost
     * 
     * @return Returns the singleCost.<br>
     */
    public BigDecimal getSingleCost() {
        return singleCost;
    }

    /**
     * set singleCost
     * 
     * @param singleCost
     *            The singleCost to set. <br>
     */
    public void setSingleCost(BigDecimal singleCost) {
        this.singleCost = singleCost;
    }

    /**
     * get roundId
     * 
     * @return Returns the roundId.<br>
     */
    public Integer getRoundId() {
        return roundId;
    }

    /**
     * set roundId
     * 
     * @param roundId
     *            The roundId to set. <br>
     */
    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    /**
     * get outId
     * 
     * @return Returns the outId.<br>
     */
    public Integer getOutId() {
        return outId;
    }

    /**
     * set outId
     * 
     * @param outId
     *            The outId to set. <br>
     */
    public void setOutId(Integer outId) {
        this.outId = outId;
    }

}
