/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年8月4日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.math.BigDecimal;

import com.tuniu.ord.common.constant.Constants;

/**
 * @author zhairongping
 *
 */
public class OrderSynInfo {
    private String sourceSystem = Constants.systemCode;
    private Integer id;
    private Integer mark = Constants.USERFUL_MARK;
    private Integer type = Constants.ORDER_TYPE;
    private String orderStatusCode;
    private String orderStatusDesc;
    private Integer state;
    private String statusDesc;
    private Integer userId;
    private String syncTime;
    private BigDecimal orderPrice;
    private BigDecimal originalPrice;
    private BigDecimal contractAmount;
    private BigDecimal actualPrice;
    private BigDecimal orderCost;
    private BigDecimal paidAmount;
    private BigDecimal depositPrice;
    private BigDecimal adultPrice;
    private BigDecimal flightPrice;
    private String currencyCode;
    private String referenceCurrencyCode;
    private String referencePrice;
    private BigDecimal payImmediate = Constants.PAY_IMMEDIATE;
    private BigDecimal insureAmount;
    private BigDecimal insureReceivableAmount;
    private BigDecimal promotionAmount;
    private BigDecimal couponAmount;
    private BigDecimal travelAmount;
    private Integer product;
    private String productName;
    private Integer productType;
    private Integer productCategory;
    private String productCategoryName;
    private Integer productSubCategory;
    private String productSubCategoryName;
    private Integer destinationFirstCategory;
    private String destinationFirstCategoryName;
    private Integer firstDestinationGrouping;
    private String firstDestinationGroupingName;
    private Integer secondDestinationGrouping;
    private String secondDestinationGroupingName;
    private Integer destination;
    private String destinationName;
    private String productIdStr;
    private Integer isSign = Constants.SIGNED;
    private String signDate;
    private String signCityCode;
    private String signCityName;
    private Integer signCompany = Constants.SIGN_COMPANY;
    private String signCompanyName;
    private Integer signType;
    private String contractId;
    private Integer isCancel;
    private Integer source;
    private Integer clientType;
    private String clearTime;
    private String startDate;
    private String backDate;
    private String createTime;
    private Integer duration;
    private Integer childCount;
    private Integer adultCount;
    private String startCityCode;
    private String startCityName;
    private String bookCityCode;
    private String bookCityName;
    private String desCityCode;
    private String desCityName;
    private String groupDateNum;
    private String selfGroupNum;
    private Integer salerId;
    private String salerName;
    private Integer csManagerId;
    private String csManagerName;
    private Integer productSalerId;
    private String productSalerName;
    private Integer productManagerId;
    private String productManagerName;
    private Integer externalOrderId;
    private Integer isSubOrder;
    private Integer primaryOrderId;
    private Integer regionCode;
    private String regionName;
    private Integer departmentCode;
    private String departmentName;
    private Integer groupId;
    private String groupName;
    // 自组团号字段名称修改
    private String selfGroypNum;
    /**
     * 归集日期
     */
    private String collectionTime;

    /**
     * @return the collectionTime
     */
    public String getCollectionTime() {
        return collectionTime;
    }

    /**
     * @param collectionTime
     *            the collectionTime to set
     */
    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getSelfGroypNum() {
        return selfGroypNum;
    }

    public void setSelfGroypNum(String selfGroypNum) {
        this.selfGroypNum = selfGroypNum;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrderStatusCode() {
        return orderStatusCode;
    }

    public void setOrderStatusCode(String orderStatusCode) {
        this.orderStatusCode = orderStatusCode;
    }

    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(String syncTime) {
        this.syncTime = syncTime;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(BigDecimal depositPrice) {
        this.depositPrice = depositPrice;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BigDecimal getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(BigDecimal flightPrice) {
        this.flightPrice = flightPrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getReferenceCurrencyCode() {
        return referenceCurrencyCode;
    }

    public void setReferenceCurrencyCode(String referenceCurrencyCode) {
        this.referenceCurrencyCode = referenceCurrencyCode;
    }

    public String getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(String referencePrice) {
        this.referencePrice = referencePrice;
    }

    public BigDecimal getPayImmediate() {
        return payImmediate;
    }

    public void setPayImmediate(BigDecimal payImmediate) {
        this.payImmediate = payImmediate;
    }

    public BigDecimal getInsureAmount() {
        return insureAmount;
    }

    public void setInsureAmount(BigDecimal insureAmount) {
        this.insureAmount = insureAmount;
    }

    public BigDecimal getInsureReceivableAmount() {
        return insureReceivableAmount;
    }

    public void setInsureReceivableAmount(BigDecimal insureReceivableAmount) {
        this.insureReceivableAmount = insureReceivableAmount;
    }

    public BigDecimal getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(BigDecimal promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getTravelAmount() {
        return travelAmount;
    }

    public void setTravelAmount(BigDecimal travelAmount) {
        this.travelAmount = travelAmount;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Integer productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Integer getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(Integer productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public String getProductSubCategoryName() {
        return productSubCategoryName;
    }

    public void setProductSubCategoryName(String productSubCategoryName) {
        this.productSubCategoryName = productSubCategoryName;
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

    public String getProductIdStr() {
        return productIdStr;
    }

    public void setProductIdStr(String productIdStr) {
        this.productIdStr = productIdStr;
    }

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getSignCityCode() {
        return signCityCode;
    }

    public void setSignCityCode(String signCityCode) {
        this.signCityCode = signCityCode;
    }

    public String getSignCityName() {
        return signCityName;
    }

    public void setSignCityName(String signCityName) {
        this.signCityName = signCityName;
    }

    public Integer getSignCompany() {
        return signCompany;
    }

    public void setSignCompany(Integer signCompany) {
        this.signCompany = signCompany;
    }

    public String getSignCompanyName() {
        return signCompanyName;
    }

    public void setSignCompanyName(String signCompanyName) {
        this.signCompanyName = signCompanyName;
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getClearTime() {
        return clearTime;
    }

    public void setClearTime(String clearTime) {
        this.clearTime = clearTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

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

    public String getBookCityCode() {
        return bookCityCode;
    }

    public void setBookCityCode(String bookCityCode) {
        this.bookCityCode = bookCityCode;
    }

    public String getBookCityName() {
        return bookCityName;
    }

    public void setBookCityName(String bookCityName) {
        this.bookCityName = bookCityName;
    }

    public String getDesCityCode() {
        return desCityCode;
    }

    public void setDesCityCode(String desCityCode) {
        this.desCityCode = desCityCode;
    }

    public String getDesCityName() {
        return desCityName;
    }

    public void setDesCityName(String desCityName) {
        this.desCityName = desCityName;
    }

    public String getGroupDateNum() {
        return groupDateNum;
    }

    public void setGroupDateNum(String groupDateNum) {
        this.groupDateNum = groupDateNum;
    }

    public String getSelfGroupNum() {
        return selfGroupNum;
    }

    public void setSelfGroupNum(String selfGroupNum) {
        this.selfGroupNum = selfGroupNum;
    }

    public Integer getSalerId() {
        return salerId;
    }

    public void setSalerId(Integer salerId) {
        this.salerId = salerId;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public Integer getCsManagerId() {
        return csManagerId;
    }

    public void setCsManagerId(Integer csManagerId) {
        this.csManagerId = csManagerId;
    }

    public String getCsManagerName() {
        return csManagerName;
    }

    public void setCsManagerName(String csManagerName) {
        this.csManagerName = csManagerName;
    }

    public Integer getProductSalerId() {
        return productSalerId;
    }

    public void setProductSalerId(Integer productSalerId) {
        this.productSalerId = productSalerId;
    }

    public String getProductSalerName() {
        return productSalerName;
    }

    public void setProductSalerName(String productSalerName) {
        this.productSalerName = productSalerName;
    }

    public Integer getProductManagerId() {
        return productManagerId;
    }

    public void setProductManagerId(Integer productManagerId) {
        this.productManagerId = productManagerId;
    }

    public String getProductManagerName() {
        return productManagerName;
    }

    public void setProductManagerName(String productManagerName) {
        this.productManagerName = productManagerName;
    }

    public Integer getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(Integer externalOrderId) {
        this.externalOrderId = externalOrderId;
    }

    public Integer getIsSubOrder() {
        return isSubOrder;
    }

    public void setIsSubOrder(Integer isSubOrder) {
        this.isSubOrder = isSubOrder;
    }

    public Integer getPrimaryOrderId() {
        return primaryOrderId;
    }

    public void setPrimaryOrderId(Integer primaryOrderId) {
        this.primaryOrderId = primaryOrderId;
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Integer departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
