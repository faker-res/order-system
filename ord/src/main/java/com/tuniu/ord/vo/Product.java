/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhairongping
 *
 */
public class Product {
    private TuniuInfo tuniuInfo;
    private AgencyInfo agencyInfo;
    private List<DepartDate> departDates;
    private List<TouristContact> touristContact;
    private List<Tourist> tourist;
    private String settleDetail;
    private BigDecimal settlePrice;
    private BigDecimal otherprice;
    private String supplyRemark;
    private String otherResources;
    /**
     * 订单备注
     */
    private String orderRemark;
    // private String schedule;
    private String priceInclude;
    private String priceExclude;
    private String remark;
    private Integer stockNum;

    public TuniuInfo getTuniuInfo() {
        return tuniuInfo;
    }

    public void setTuniuInfo(TuniuInfo tuniuInfo) {
        this.tuniuInfo = tuniuInfo;
    }

    public AgencyInfo getAgencyInfo() {
        return agencyInfo;
    }

    public void setAgencyInfo(AgencyInfo agencyInfo) {
        this.agencyInfo = agencyInfo;
    }

    public List<DepartDate> getDepartDates() {
        return departDates;
    }

    public void setDepartDates(List<DepartDate> departDates) {
        this.departDates = departDates;
    }

    public List<TouristContact> getTouristContact() {
        return touristContact;
    }

    public void setTouristContact(List<TouristContact> touristContact) {
        this.touristContact = touristContact;
    }

    public List<Tourist> getTourist() {
        return tourist;
    }

    public void setTourist(List<Tourist> tourist) {
        this.tourist = tourist;
    }

    public String getSettleDetail() {
        return settleDetail;
    }

    public void setSettleDetail(String settleDetail) {
        this.settleDetail = settleDetail;
    }

    public BigDecimal getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(BigDecimal settlePrice) {
        this.settlePrice = settlePrice;
    }

    public BigDecimal getOtherprice() {
        return otherprice;
    }

    public void setOtherprice(BigDecimal otherprice) {
        this.otherprice = otherprice;
    }

    public String getSupplyRemark() {
        return supplyRemark;
    }

    public void setSupplyRemark(String supplyRemark) {
        this.supplyRemark = supplyRemark;
    }

    public String getOtherResources() {
        return otherResources;
    }

    public void setOtherResources(String otherResources) {
        this.otherResources = otherResources;
    }

    // public String getSchedule() {
    // return schedule;
    // }
    //
    // public void setSchedule(String schedule) {
    // this.schedule = schedule;
    // }

    public String getPriceInclude() {
        return priceInclude;
    }

    public void setPriceInclude(String priceInclude) {
        this.priceInclude = priceInclude;
    }

    public String getPriceExclude() {
        return priceExclude;
    }

    public void setPriceExclude(String priceExclude) {
        this.priceExclude = priceExclude;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    /**
     * @return the orderRemark
     */
    public String getOrderRemark() {
        return orderRemark;
    }

    /**
     * @param orderRemark
     *            the orderRemark to set
     */
    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

}
