/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年4月20日                                                      
 *                                                                              
 *******************************************************************************/
package com.tuniu.ord.vo.external;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 产品查询结果
 * 
 * @author zhairongping
 * 
 */
public class ProductQueryOutputVo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* 产品基本信息 */
    private ProductBase productBase;
    /* 产品出发地 */
    private ProductDepartureCity productDepartureCity;
    /* 产品目的地 */
    private ProductDestination productDestination;
    /* 产品责任人 */
    private ProductRelatePerson productRelatePerson;
    /* 渠道信息 */
    private ProductSellChannel productSellChannel;

    /**
     * 天数
     */
    private Integer dayNum;

    private Integer tourBasicId;

    private String tourGroupName;

    private BigDecimal adultPrice;

    private BigDecimal childPrice;

    public Integer getTourBasicId() {
        return tourBasicId;
    }

    public void setTourBasicId(Integer tourBasicId) {
        this.tourBasicId = tourBasicId;
    }

    public ProductBase getProductBase() {
        return productBase;
    }

    public void setProductBase(ProductBase productBase) {
        this.productBase = productBase;
    }

    public ProductDepartureCity getProductDepartureCity() {
        return productDepartureCity;
    }

    public void setProductDepartureCity(ProductDepartureCity productDepartureCity) {
        this.productDepartureCity = productDepartureCity;
    }

    public ProductDestination getProductDestination() {
        return productDestination;
    }

    public void setProductDestination(ProductDestination productDestination) {
        this.productDestination = productDestination;
    }

    public ProductRelatePerson getProductRelatePerson() {
        return productRelatePerson;
    }

    public void setProductRelatePerson(ProductRelatePerson productRelatePerson) {
        this.productRelatePerson = productRelatePerson;
    }

    public ProductSellChannel getProductSellChannel() {
        return productSellChannel;
    }

    public void setProductSellChannel(ProductSellChannel productSellChannel) {
        this.productSellChannel = productSellChannel;
    }

    public String getTourGroupName() {
        return tourGroupName;
    }

    public void setTourGroupName(String tourGroupName) {
        this.tourGroupName = tourGroupName;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BigDecimal getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

}