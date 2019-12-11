package com.tuniu.ord.vo.external;

import com.tuniu.ord.domain.BaseDomain;

/**
 * ProductDepartureCity product_departure_city
 */
public class ProductDepartureCity extends BaseDomain {

    private static final long serialVersionUID = -5054603853155813038L;

    /**
     * product_departure_city.product_id
     */
    private Integer productId;

    /**
     * 出发城市编号 product_departure_city.departure_city_code
     */
    private Integer departureCityCode;

    /**
     * 出发城市名称 product_departure_city.departure_city_name
     */
    private String departureCityName;

    /**
     * @return product_departure_city.product_id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param Integer
     *            productId (product_departure_city.product_id )
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return product_departure_city.departure_city_code
     */
    public Integer getDepartureCityCode() {
        return departureCityCode;
    }

    /**
     * @param Integer
     *            departureCityCode (product_departure_city.departure_city_code )
     */
    public void setDepartureCityCode(Integer departureCityCode) {
        this.departureCityCode = departureCityCode;
    }

    /**
     * @return product_departure_city.departure_city_name
     */
    public String getDepartureCityName() {
        return departureCityName;
    }

    /**
     * @param String
     *            departureCityName (product_departure_city.departure_city_name )
     */
    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName == null ? null : departureCityName.trim();
    }

}