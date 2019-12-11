/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * @author zhairongping
 *
 */
public class DProductStock {
    private Integer dProductId;
    private Integer dProductType;
    private Integer vendorId;
    private String vendorName;
    private Integer costCurrencyType;
    private List<DepartDateStock> departDates;

    public Integer getdProductId() {
        return dProductId;
    }

    public void setdProductId(Integer dProductId) {
        this.dProductId = dProductId;
    }

    public Integer getdProductType() {
        return dProductType;
    }

    public void setdProductType(Integer dProductType) {
        this.dProductType = dProductType;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Integer getCostCurrencyType() {
        return costCurrencyType;
    }

    public void setCostCurrencyType(Integer costCurrencyType) {
        this.costCurrencyType = costCurrencyType;
    }

    public List<DepartDateStock> getDepartDates() {
        return departDates;
    }

    public void setDepartDates(List<DepartDateStock> departDates) {
        this.departDates = departDates;
    }

}
