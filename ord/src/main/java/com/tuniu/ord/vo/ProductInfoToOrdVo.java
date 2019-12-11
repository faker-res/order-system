/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月7日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 *
 */
public class ProductInfoToOrdVo {
    private String startCityCode;
    private String startCityName;
    private Integer destinationFirstCategory;
    private String destinationFirstCategoryName;
    private Integer firstDestinationGrouping;
    private String firstDestinationGroupingName;
    private Integer secondDestinationGrouping;
    private String secondDestinationGroupingName;
    private Integer destination;
    private String destinationName;
    private Integer productLineId;

    public String getStartCityCode() {
        return startCityCode;
    }

    public void setStartCityCode(String startCityCode) {
        this.startCityCode = startCityCode;
    }

    public String getStartCityName() {
        return startCityName;
    }

    public void setStartCityName(String startCityName) {
        this.startCityName = startCityName;
    }

    public Integer getDestinationFirstCategory() {
        return destinationFirstCategory;
    }

    public void setDestinationFirstCategory(Integer destinationFirstCategory) {
        this.destinationFirstCategory = destinationFirstCategory;
    }

    public String getDestinationFirstCategoryName() {
        return destinationFirstCategoryName;
    }

    public void setDestinationFirstCategoryName(String destinationFirstCategoryName) {
        this.destinationFirstCategoryName = destinationFirstCategoryName;
    }

    public Integer getFirstDestinationGrouping() {
        return firstDestinationGrouping;
    }

    public void setFirstDestinationGrouping(Integer firstDestinationGrouping) {
        this.firstDestinationGrouping = firstDestinationGrouping;
    }

    public String getFirstDestinationGroupingName() {
        return firstDestinationGroupingName;
    }

    public void setFirstDestinationGroupingName(String firstDestinationGroupingName) {
        this.firstDestinationGroupingName = firstDestinationGroupingName;
    }

    public Integer getSecondDestinationGrouping() {
        return secondDestinationGrouping;
    }

    public void setSecondDestinationGrouping(Integer secondDestinationGrouping) {
        this.secondDestinationGrouping = secondDestinationGrouping;
    }

    public String getSecondDestinationGroupingName() {
        return secondDestinationGroupingName;
    }

    public void setSecondDestinationGroupingName(String secondDestinationGroupingName) {
        this.secondDestinationGroupingName = secondDestinationGroupingName;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Integer getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(Integer productLineId) {
        this.productLineId = productLineId;
    }

}
