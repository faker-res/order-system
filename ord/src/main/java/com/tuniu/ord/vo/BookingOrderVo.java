/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-29                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.math.BigDecimal;

import com.tuniu.ord.domain.BaseDomain;

/**
 * <Description> <br>
 * 
 * @author gaofei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-5-29 <br>
 */

public class BookingOrderVo extends BaseDomain {
    private static final long serialVersionUID = 1775365865675706296L;

    /**
     * productId 产品ID
     */
    private Integer productId;

    /**
     * productName 产品名称
     */
    private String productName;

    /**
     * groupId 团模版ID
     */
    private Integer groupId;

    /**
     * groupName 团名称
     */
    private String groupName;

    /**
     * stockAdultCount 库存成人数
     */
    private Integer stockAdultCount;

    /**
     * stockChildCount 库存儿童数
     */
    private Integer stockChildCount;

    /**
     * adultPrice
     */
    private BigDecimal adultPrice;

    /**
     * childPrice
     */
    private BigDecimal childPrice;

    /**
     * currencyType 币种
     */
    private String currencyType;

    /**
     * orderDates
     */
    private OrderDepartDates[] orderDates;

    /**
     * distributorId 分销商ID
     */
    private String distributorId;

    /**
     * distributorName
     */
    private String distributorName;

    /**
     * firstDestGroupId 一级目的地分组ID
     */
    private Integer firstDestGroupId;

    /**
     * firstGestGroupName 一级目的地分组名称
     */
    private String firstDestGroupName;

    /**
     * secDestGroupId 二级目的地分组ID
     */
    private Integer secDestGroupId;

    /**
     * secDestGroupName 二级目的地分组名称
     */
    private String secDestGroupName;

    /**
     * destCategoryId 目的地大类ID
     */
    private Integer destCategoryId;

    /**
     * destCategoryName 目的地大类名称
     */
    private String destCategoryName;

    /**
     * 产品专员ID ord_deal_order.product_staff_id
     */
    private Integer productStaffId;

    /**
     * 产品专员姓名 ord_deal_order.product_staff_name
     */
    private String productStaffName;

    /**
     * productManagerId 产品经理ID
     */
    private Integer productManagerId;

    /**
     * productManagerName 产品经理姓名
     */
    private String productManagerName;

    /**
     * departure_city_code 出发城市编号
     */
    private Integer departureCityCode;

    /**
     * departure_city_name 出发城市名称
     */
    private String departureCityName;

    /**
     * continent_code 洲code
     */
    private Integer continentCode;

    /**
     * continent_name 洲名称
     */
    private String continentName;

    /**
     * country_code 国家CODE
     */
    private Integer countryCode;

    /**
     * country_name 国家名称
     */
    private String countryName;

    /**
     * province_code 省份CODE
     */
    private Integer provinceCode;

    /**
     * province_name 省份名称
     */
    private String provinceName;

    /**
     * city_code 城市CODE
     */
    private Integer cityCode;

    /**
     * city_name 城市名称
     */
    private String cityName;

    /**
     * districtCode 区县CODE
     */
    private Integer districtCode;

    /**
     * district_name 区县名称
     */
    private String districtName;

    /**
     * product_category 产品一级品类ID
     */
    private Integer productCategory;

    /**
     * product_category_name 产品一级品类名称
     */
    private String productCategoryName;

    /**
     * product_sub_category 产品二级品类ID
     */
    private Integer productSubCategory;

    /**
     * product_sub_category_name 产品二级品类名称
     */
    private String productSubCategoryName;

    /**
     * dest_id 目的地ID
     */
    private Integer destId;

    /**
     * dest_name 目的名称
     */
    private String destName;

    /**
     * product_line_id 产品线ID
     */
    private Integer productLineId;

    private int dayNum;

    /**
     * 1 库存 2 现询
     */
    private int channelType;

    private String sellChannelCode;

    /**
     * get productId
     * 
     * @return Returns the productId.<br>
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * set productId
     * 
     * @param productId
     *            The productId to set. <br>
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * get productName
     * 
     * @return Returns the productName.<br>
     */
    public String getProductName() {
        return productName;
    }

    /**
     * set productName
     * 
     * @param productName
     *            The productName to set. <br>
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * get groupId
     * 
     * @return Returns the groupId.<br>
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * set groupId
     * 
     * @param groupId
     *            The groupId to set. <br>
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * get groupName
     * 
     * @return Returns the groupName.<br>
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * set groupName
     * 
     * @param groupName
     *            The groupName to set. <br>
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * get stockAdultCount
     * 
     * @return Returns the stockAdultCount.<br>
     */
    public Integer getStockAdultCount() {
        return stockAdultCount;
    }

    /**
     * set stockAdultCount
     * 
     * @param stockAdultCount
     *            The stockAdultCount to set. <br>
     */
    public void setStockAdultCount(Integer stockAdultCount) {
        this.stockAdultCount = stockAdultCount;
    }

    /**
     * get stockChildCount
     * 
     * @return Returns the stockChildCount.<br>
     */
    public Integer getStockChildCount() {
        return stockChildCount;
    }

    /**
     * set stockChildCount
     * 
     * @param stockChildCount
     *            The stockChildCount to set. <br>
     */
    public void setStockChildCount(Integer stockChildCount) {
        this.stockChildCount = stockChildCount;
    }

    /**
     * get adultPrice
     * 
     * @return Returns the adultPrice.<br>
     */
    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    /**
     * set adultPrice
     * 
     * @param adultPrice
     *            The adultPrice to set. <br>
     */
    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    /**
     * get childPrice
     * 
     * @return Returns the childPrice.<br>
     */
    public BigDecimal getChildPrice() {
        return childPrice;
    }

    /**
     * set childPrice
     * 
     * @param childPrice
     *            The childPrice to set. <br>
     */
    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    /**
     * get currencyType
     * 
     * @return Returns the currencyType.<br>
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * set currencyType
     * 
     * @param currencyType
     *            The currencyType to set. <br>
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * get orderDates
     * 
     * @return Returns the orderDates.<br>
     */
    public OrderDepartDates[] getOrderDates() {
        return orderDates;
    }

    /**
     * set orderDates
     * 
     * @param orderDates
     *            The orderDates to set. <br>
     */
    public void setOrderDates(OrderDepartDates[] orderDates) {
        this.orderDates = orderDates;
    }

    /**
     * get distributorId
     * 
     * @return Returns the distributorId.<br>
     */
    public String getDistributorId() {
        return distributorId;
    }

    /**
     * set distributorId
     * 
     * @param distributorId
     *            The distributorId to set. <br>
     */
    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
    }

    /**
     * get firstDestGroupId
     * 
     * @return Returns the firstDestGroupId.<br>
     */
    public Integer getFirstDestGroupId() {
        return firstDestGroupId;
    }

    /**
     * set firstDestGroupId
     * 
     * @param firstDestGroupId
     *            The firstDestGroupId to set. <br>
     */
    public void setFirstDestGroupId(Integer firstDestGroupId) {
        this.firstDestGroupId = firstDestGroupId;
    }

    /**
     * get firstDestGroupName
     * 
     * @return Returns the firstDestGroupName.<br>
     */
    public String getFirstDestGroupName() {
        return firstDestGroupName;
    }

    /**
     * set firstDestGroupName
     * 
     * @param firstDestGroupName
     *            The firstDestGroupName to set. <br>
     */
    public void setFirstDestGroupName(String firstDestGroupName) {
        this.firstDestGroupName = firstDestGroupName;
    }

    /**
     * get secDestGroupId
     * 
     * @return Returns the secDestGroupId.<br>
     */
    public Integer getSecDestGroupId() {
        return secDestGroupId;
    }

    /**
     * set secDestGroupId
     * 
     * @param secDestGroupId
     *            The secDestGroupId to set. <br>
     */
    public void setSecDestGroupId(Integer secDestGroupId) {
        this.secDestGroupId = secDestGroupId;
    }

    /**
     * get secDestGroupName
     * 
     * @return Returns the secDestGroupName.<br>
     */
    public String getSecDestGroupName() {
        return secDestGroupName;
    }

    /**
     * set secDestGroupName
     * 
     * @param secDestGroupName
     *            The secDestGroupName to set. <br>
     */
    public void setSecDestGroupName(String secDestGroupName) {
        this.secDestGroupName = secDestGroupName;
    }

    /**
     * get destCategoryId
     * 
     * @return Returns the destCategoryId.<br>
     */
    public Integer getDestCategoryId() {
        return destCategoryId;
    }

    /**
     * set destCategoryId
     * 
     * @param destCategoryId
     *            The destCategoryId to set. <br>
     */
    public void setDestCategoryId(Integer destCategoryId) {
        this.destCategoryId = destCategoryId;
    }

    /**
     * get destCategoryName
     * 
     * @return Returns the destCategoryName.<br>
     */
    public String getDestCategoryName() {
        return destCategoryName;
    }

    /**
     * set destCategoryName
     * 
     * @param destCategoryName
     *            The destCategoryName to set. <br>
     */
    public void setDestCategoryName(String destCategoryName) {
        this.destCategoryName = destCategoryName;
    }

    public Integer getProductStaffId() {
        return productStaffId;
    }

    public void setProductStaffId(Integer productStaffId) {
        this.productStaffId = productStaffId;
    }

    public String getProductStaffName() {
        return productStaffName;
    }

    public void setProductStaffName(String productStaffName) {
        this.productStaffName = productStaffName;
    }

    /**
     * get productManagerId
     * 
     * @return Returns the productManagerId.<br>
     */
    public Integer getProductManagerId() {
        return productManagerId;
    }

    /**
     * set productManagerId
     * 
     * @param productManagerId
     *            The productManagerId to set. <br>
     */
    public void setProductManagerId(Integer productManagerId) {
        this.productManagerId = productManagerId;
    }

    /**
     * get productManagerName
     * 
     * @return Returns the productManagerName.<br>
     */
    public String getProductManagerName() {
        return productManagerName;
    }

    /**
     * set productManagerName
     * 
     * @param productManagerName
     *            The productManagerName to set. <br>
     */
    public void setProductManagerName(String productManagerName) {
        this.productManagerName = productManagerName;
    }

    /**
     * get distributorName
     * 
     * @return Returns the distributorName.<br>
     */
    public String getDistributorName() {
        return distributorName;
    }

    /**
     * set distributorName
     * 
     * @param distributorName
     *            The distributorName to set. <br>
     */
    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    /**
     * get serialversionuid
     * 
     * @return Returns the serialversionuid.<br>
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * get departureCityCode
     * 
     * @return Returns the departureCityCode.<br>
     */
    public Integer getDepartureCityCode() {
        return departureCityCode;
    }

    /**
     * set departureCityCode
     * 
     * @param departureCityCode
     *            The departureCityCode to set. <br>
     */
    public void setDepartureCityCode(Integer departureCityCode) {
        this.departureCityCode = departureCityCode;
    }

    /**
     * get departureCityName
     * 
     * @return Returns the departureCityName.<br>
     */
    public String getDepartureCityName() {
        return departureCityName;
    }

    /**
     * set departureCityName
     * 
     * @param departureCityName
     *            The departureCityName to set. <br>
     */
    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    /**
     * get continentCode
     * 
     * @return Returns the continentCode.<br>
     */
    public Integer getContinentCode() {
        return continentCode;
    }

    /**
     * set continentCode
     * 
     * @param continentCode
     *            The continentCode to set. <br>
     */
    public void setContinentCode(Integer continentCode) {
        this.continentCode = continentCode;
    }

    /**
     * get continentName
     * 
     * @return Returns the continentName.<br>
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     * set continentName
     * 
     * @param continentName
     *            The continentName to set. <br>
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    /**
     * get countryCode
     * 
     * @return Returns the countryCode.<br>
     */
    public Integer getCountryCode() {
        return countryCode;
    }

    /**
     * set countryCode
     * 
     * @param countryCode
     *            The countryCode to set. <br>
     */
    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * get countryName
     * 
     * @return Returns the countryName.<br>
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * set countryName
     * 
     * @param countryName
     *            The countryName to set. <br>
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * get provinceCode
     * 
     * @return Returns the provinceCode.<br>
     */
    public Integer getProvinceCode() {
        return provinceCode;
    }

    /**
     * set provinceCode
     * 
     * @param provinceCode
     *            The provinceCode to set. <br>
     */
    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * get provinceName
     * 
     * @return Returns the provinceName.<br>
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * set provinceName
     * 
     * @param provinceName
     *            The provinceName to set. <br>
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * get cityCode
     * 
     * @return Returns the cityCode.<br>
     */
    public Integer getCityCode() {
        return cityCode;
    }

    /**
     * set cityCode
     * 
     * @param cityCode
     *            The cityCode to set. <br>
     */
    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * get cityName
     * 
     * @return Returns the cityName.<br>
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * set cityName
     * 
     * @param cityName
     *            The cityName to set. <br>
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * get districtCode
     * 
     * @return Returns the districtCode.<br>
     */
    public Integer getDistrictCode() {
        return districtCode;
    }

    /**
     * set districtCode
     * 
     * @param districtCode
     *            The districtCode to set. <br>
     */
    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * get districtName
     * 
     * @return Returns the districtName.<br>
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * set districtName
     * 
     * @param districtName
     *            The districtName to set. <br>
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * get productCategory
     * 
     * @return Returns the productCategory.<br>
     */
    public Integer getProductCategory() {
        return productCategory;
    }

    /**
     * set productCategory
     * 
     * @param productCategory
     *            The productCategory to set. <br>
     */
    public void setProductCategory(Integer productCategory) {
        this.productCategory = productCategory;
    }

    /**
     * get productCategoryName
     * 
     * @return Returns the productCategoryName.<br>
     */
    public String getProductCategoryName() {
        return productCategoryName;
    }

    /**
     * set productCategoryName
     * 
     * @param productCategoryName
     *            The productCategoryName to set. <br>
     */
    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    /**
     * get productSubCategory
     * 
     * @return Returns the productSubCategory.<br>
     */
    public Integer getProductSubCategory() {
        return productSubCategory;
    }

    /**
     * set productSubCategory
     * 
     * @param productSubCategory
     *            The productSubCategory to set. <br>
     */
    public void setProductSubCategory(Integer productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    /**
     * get productSubCategoryName
     * 
     * @return Returns the productSubCategoryName.<br>
     */
    public String getProductSubCategoryName() {
        return productSubCategoryName;
    }

    /**
     * set productSubCategoryName
     * 
     * @param productSubCategoryName
     *            The productSubCategoryName to set. <br>
     */
    public void setProductSubCategoryName(String productSubCategoryName) {
        this.productSubCategoryName = productSubCategoryName;
    }

    /**
     * get destId
     * 
     * @return Returns the destId.<br>
     */
    public Integer getDestId() {
        return destId;
    }

    /**
     * set destId
     * 
     * @param destId
     *            The destId to set. <br>
     */
    public void setDestId(Integer destId) {
        this.destId = destId;
    }

    /**
     * get destName
     * 
     * @return Returns the destName.<br>
     */
    public String getDestName() {
        return destName;
    }

    /**
     * set destName
     * 
     * @param destName
     *            The destName to set. <br>
     */
    public void setDestName(String destName) {
        this.destName = destName;
    }

    /**
     * get productLineId
     * 
     * @return Returns the productLineId.<br>
     */
    public Integer getProductLineId() {
        return productLineId;
    }

    /**
     * set productLineId
     * 
     * @param productLineId
     *            The productLineId to set. <br>
     */
    public void setProductLineId(Integer productLineId) {
        this.productLineId = productLineId;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    public String getSellChannelCode() {
        return sellChannelCode;
    }

    public void setSellChannelCode(String sellChannelCode) {
        this.sellChannelCode = sellChannelCode;
    }

}
