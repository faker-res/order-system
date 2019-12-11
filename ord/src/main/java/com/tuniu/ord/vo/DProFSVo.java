/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年4月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * @author zhairongping
 *
 */
public class DProFSVo {
    /**
     * D产品ID
     */
    private Integer dProductId;
    /**
     * D产品名称
     */
    private String dProductName;
    /**
     * 资源类型(1111)
     */
    private Integer dProductType;

    /**
     * 产品负责人ID
     */
    private Integer productLeaderId;
    /**
     * 产品负责人名称
     */
    private String productLeaderName;

    /**
     * 币种
     */
    private Integer costCurrencyType;
    /**
     * 供应商ID
     */
    private Integer vendorId;
    /**
     * 供应商名称
     */
    private String vendorName;
    /**
     * 清位时间，格式:yyyy-MM-dd HH:mm:ss
     */
    private String releaseTime;
    /**
     * 库存类型 1-控位 2-FS 3-现询
     */
    private Integer stockType;
    /**
     * 产品入即占团期信息
     */
    private List<DepartDateFSVo> departDates;

    /**
     * @return the dProductId
     */
    public Integer getdProductId() {
        return dProductId;
    }

    /**
     * @param dProductId
     *            the dProductId to set
     */
    public void setdProductId(Integer dProductId) {
        this.dProductId = dProductId;
    }

    /**
     * @return the dProductName
     */
    public String getdProductName() {
        return dProductName;
    }

    /**
     * @param dProductName
     *            the dProductName to set
     */
    public void setdProductName(String dProductName) {
        this.dProductName = dProductName;
    }

    /**
     * @return the dProductType
     */
    public Integer getdProductType() {
        return dProductType;
    }

    /**
     * @param dProductType
     *            the dProductType to set
     */
    public void setdProductType(Integer dProductType) {
        this.dProductType = dProductType;
    }

    /**
     * @return the costCurrencyType
     */
    public Integer getCostCurrencyType() {
        return costCurrencyType;
    }

    /**
     * @param costCurrencyType
     *            the costCurrencyType to set
     */
    public void setCostCurrencyType(Integer costCurrencyType) {
        this.costCurrencyType = costCurrencyType;
    }

    /**
     * @return the vendorId
     */
    public Integer getVendorId() {
        return vendorId;
    }

    /**
     * @param vendorId
     *            the vendorId to set
     */
    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * @return the vendorName
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * @param vendorName
     *            the vendorName to set
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * @return the releaseTime
     */
    public String getReleaseTime() {
        return releaseTime;
    }

    /**
     * @param releaseTime
     *            the releaseTime to set
     */
    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * @return the stockType
     */
    public Integer getStockType() {
        return stockType;
    }

    /**
     * @param stockType
     *            the stockType to set
     */
    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    /**
     * @return the departDates
     */
    public List<DepartDateFSVo> getDepartDates() {
        return departDates;
    }

    /**
     * @param departDates
     *            the departDates to set
     */
    public void setDepartDates(List<DepartDateFSVo> departDates) {
        this.departDates = departDates;
    }

    /**
     * @return the productLeaderId
     */
    public Integer getProductLeaderId() {
        return productLeaderId;
    }

    /**
     * @param productLeaderId
     *            the productLeaderId to set
     */
    public void setProductLeaderId(Integer productLeaderId) {
        this.productLeaderId = productLeaderId;
    }

    /**
     * @return the productLeaderName
     */
    public String getProductLeaderName() {
        return productLeaderName;
    }

    /**
     * @param productLeaderName
     *            the productLeaderName to set
     */
    public void setProductLeaderName(String productLeaderName) {
        this.productLeaderName = productLeaderName;
    }

}
