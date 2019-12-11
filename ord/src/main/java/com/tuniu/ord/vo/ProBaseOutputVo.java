/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年4月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 *
 */
public class ProBaseOutputVo {
    private Integer id;
    private String productName;

    private Integer destCategoryId;
    private String destCategoryName;
    private Integer firstDestGroupId;
    private String firstDestGroupName;
    private Integer secDestGroupId;
    private String secDestGroupName;
    private Integer destId;
    private String destName;
    private Integer productLineId;

    private Integer departureCityCode;
    private String departureCityName;
    private Integer continentCode;
    private String continentName;
    private Integer countryCode;
    private String countryName;
    private Integer provinceCode;
    private String provinceName;
    private Integer cityCode;
    private String cityName;
    private Integer districtCode;
    private String districtName;

    /**
     * 产品负责人ID
     */
    private Integer userId;
    /**
     * 产品负责人姓名
     */
    private String name;

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     *            the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the destCategoryId
     */
    public Integer getDestCategoryId() {
        return destCategoryId;
    }

    /**
     * @param destCategoryId
     *            the destCategoryId to set
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
     * @param destCategoryName
     *            the destCategoryName to set
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
     * @param firstDestGroupId
     *            the firstDestGroupId to set
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
     * @param firstDestGroupName
     *            the firstDestGroupName to set
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
     * @param secDestGroupId
     *            the secDestGroupId to set
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
     * @param secDestGroupName
     *            the secDestGroupName to set
     */
    public void setSecDestGroupName(String secDestGroupName) {
        this.secDestGroupName = secDestGroupName;
    }

    /**
     * @return the destId
     */
    public Integer getDestId() {
        return destId;
    }

    /**
     * @param destId
     *            the destId to set
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
     * @param destName
     *            the destName to set
     */
    public void setDestName(String destName) {
        this.destName = destName;
    }

    /**
     * @return the productLineId
     */
    public Integer getProductLineId() {
        return productLineId;
    }

    /**
     * @param productLineId
     *            the productLineId to set
     */
    public void setProductLineId(Integer productLineId) {
        this.productLineId = productLineId;
    }

    /**
     * @return the departureCityCode
     */
    public Integer getDepartureCityCode() {
        return departureCityCode;
    }

    /**
     * @param departureCityCode
     *            the departureCityCode to set
     */
    public void setDepartureCityCode(Integer departureCityCode) {
        this.departureCityCode = departureCityCode;
    }

    /**
     * @return the departureCityName
     */
    public String getDepartureCityName() {
        return departureCityName;
    }

    /**
     * @param departureCityName
     *            the departureCityName to set
     */
    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    /**
     * @return the continentCode
     */
    public Integer getContinentCode() {
        return continentCode;
    }

    /**
     * @param continentCode
     *            the continentCode to set
     */
    public void setContinentCode(Integer continentCode) {
        this.continentCode = continentCode;
    }

    /**
     * @return the continentName
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     * @param continentName
     *            the continentName to set
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    /**
     * @return the countryCode
     */
    public Integer getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode
     *            the countryCode to set
     */
    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName
     *            the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the provinceCode
     */
    public Integer getProvinceCode() {
        return provinceCode;
    }

    /**
     * @param provinceCode
     *            the provinceCode to set
     */
    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * @return the provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName
     *            the provinceName to set
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * @return the cityCode
     */
    public Integer getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode
     *            the cityCode to set
     */
    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName
     *            the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return the districtCode
     */
    public Integer getDistrictCode() {
        return districtCode;
    }

    /**
     * @param districtCode
     *            the districtCode to set
     */
    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * @return the districtName
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * @param districtName
     *            the districtName to set
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

}
