package com.tuniu.ord.vo.external;

import com.tuniu.ord.domain.BaseDomain;

/**
 * ProductDestination product_destination
 */
public class ProductDestination extends BaseDomain {

    private static final long serialVersionUID = 4976721265758198000L;

    /**
     * product_destination.product_id
     */
    private Integer productId;

    /**
     * 是否为主目的地，0:否；1:是； product_destination.is_main
     */
    private Byte isMain;

    /**
     * 洲code product_destination.continent_code
     */
    private Integer continentCode;

    /**
     * 洲名称 product_destination.continent_name
     */
    private String continentName;

    /**
     * 国家CODE product_destination.country_code
     */
    private Integer countryCode;

    /**
     * 国家名称 product_destination.country_name
     */
    private String countryName;

    /**
     * 省份CODE product_destination.province_code
     */
    private Integer provinceCode;

    /**
     * 省份名称 product_destination.province_name
     */
    private String provinceName;

    /**
     * 城市CODE product_destination.city_code
     */
    private Integer cityCode;

    /**
     * 城市名称 product_destination.city_name
     */
    private String cityName;

    /**
     * 区县CODE product_destination.district_code
     */
    private Integer districtCode;

    /**
     * 区县名称 product_destination.district_name
     */
    private String districtName;

    /**
     * @return product_destination.product_id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param Integer
     *            productId (product_destination.product_id )
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return product_destination.is_main
     */
    public Byte getIsMain() {
        return isMain;
    }

    /**
     * @param Byte
     *            isMain (product_destination.is_main )
     */
    public void setIsMain(Byte isMain) {
        this.isMain = isMain;
    }

    /**
     * @return product_destination.continent_code
     */
    public Integer getContinentCode() {
        return continentCode;
    }

    /**
     * @param Integer
     *            continentCode (product_destination.continent_code )
     */
    public void setContinentCode(Integer continentCode) {
        this.continentCode = continentCode;
    }

    /**
     * @return product_destination.continent_name
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     * @param String
     *            continentName (product_destination.continent_name )
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName == null ? null : continentName.trim();
    }

    /**
     * @return product_destination.country_code
     */
    public Integer getCountryCode() {
        return countryCode;
    }

    /**
     * @param Integer
     *            countryCode (product_destination.country_code )
     */
    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return product_destination.country_name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param String
     *            countryName (product_destination.country_name )
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    /**
     * @return product_destination.province_code
     */
    public Integer getProvinceCode() {
        return provinceCode;
    }

    /**
     * @param Integer
     *            provinceCode (product_destination.province_code )
     */
    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * @return product_destination.province_name
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param String
     *            provinceName (product_destination.province_name )
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * @return product_destination.city_code
     */
    public Integer getCityCode() {
        return cityCode;
    }

    /**
     * @param Integer
     *            cityCode (product_destination.city_code )
     */
    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * @return product_destination.city_name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param String
     *            cityName (product_destination.city_name )
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * @return product_destination.district_code
     */
    public Integer getDistrictCode() {
        return districtCode;
    }

    /**
     * @param Integer
     *            districtCode (product_destination.district_code )
     */
    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * @return product_destination.district_name
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * @param String
     *            districtName (product_destination.district_name )
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

}