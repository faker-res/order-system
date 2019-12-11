/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Wed May 25 13:39:49 CST 2016
 * Description:
 */
package com.tuniu.ord.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrdDealOrder ord_deal_order
 */
public class OrdDealOrder extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 订单号 ord_deal_order.order_id
     */
    private Integer orderId;

    /**
     * 产品编号 ord_deal_order.product_id
     */
    private Integer productId;

    /**
     * 一级目的地分组ID ord_deal_order.first_dest_group_id
     */
    private Integer firstDestGroupId;

    /**
     * 目的地大类ID ord_deal_order.dest_category_id
     */
    private Integer destCategoryId;

    /**
     * 目的地大类名称 ord_deal_order.dest_category_name
     */
    private String destCategoryName;

    /**
     * 一级目的地分组名称 ord_deal_order.first_dest_group_name
     */
    private String firstDestGroupName;

    /**
     * 二级目的地分组ID ord_deal_order.sec_dest_group_id
     */
    private Integer secDestGroupId;

    /**
     * 二级目的地分组名称 ord_deal_order.sec_dest_group_name
     */
    private String secDestGroupName;

    /**
     * 产品名称 ord_deal_order.product_name
     */
    private String productName;

    /**
     * 团编号 ord_deal_order.group_id
     */
    private Integer groupId;

    /**
     * 团名称 ord_deal_order.group_name
     */
    private String groupName;

    /**
     * 团期 ord_deal_order.depart_date
     */
    private Date departDate;

    /**
     * 关团 ord_deal_order.closing_date
     */
    private Date closingDate;

    /**
     * 分销商ID ord_deal_order.distributor_id
     */
    private String distributorId;

    /**
     * 订单状态 ord_deal_order.status_code
     */
    private String statusCode;

    /**
     * 客服专员ID ord_deal_order.customer_staff_id
     */
    private Integer customerStaffId;

    /**
     * 客服专员姓名 ord_deal_order.customer_staff_name
     */
    private String customerStaffName;

    /**
     * 客服经理ID ord_deal_order.customer_manager_id
     */
    private Integer customerManagerId;

    /**
     * 客服经理姓名 ord_deal_order.customer_manager_name
     */
    private String customerManagerName;

    /**
     * 产品专员ID ord_deal_order.product_staff_id
     */
    private Integer productStaffId;

    /**
     * 产品专员姓名 ord_deal_order.product_staff_name
     */
    private String productStaffName;

    /**
     * 产品经理ID ord_deal_order.product_manager_id
     */
    private Integer productManagerId;

    /**
     * 产品经理姓名 ord_deal_order.product_manager_name
     */
    private String productManagerName;

    /**
     * 运营专员ID ord_deal_order.operation_staff_id
     */
    private Integer operationStaffId;

    /**
     * 运营专员姓名 ord_deal_order.operation_staff_name
     */
    private String operationStaffName;

    /**
     * 运营经理ID ord_deal_order.operation_manager_id
     */
    private Integer operationManagerId;

    /**
     * 运营经理姓名 ord_deal_order.operation_manager_name
     */
    private String operationManagerName;

    /**
     * A资源入库批次号 ord_deal_order.ext_batch_id
     */
    private Integer extBatchId;

    /**
     * D入库批次号 ord_deal_order.ext_dbatch_id
     */
    private Integer extDbatchId;

    /**
     * D占位单id ord_deal_order.occupy_batch_id
     */
    private Integer occupyBatchId;

    /**
     * D出库单ID
     */
    private Integer outLibraryId;

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

    /**
     * 1 库存 2 现询
     */
    private Integer dealOrderType;

    /**
     * 天数
     */
    private Integer dayNum;

    /**
     * 归来日期
     */
    private Date backDate;

    /**
     * 切位单数量
     */
    private Integer dealOrderNum;

    /**
     * 成人价
     */
    private BigDecimal adultPrice;

    /**
     * 儿童价
     */
    private BigDecimal childPrice;

    /**
     * 渠道名称
     */
    private String sellChannelCode;

    /**
     * 手工下单的占位数量
     */
    private Integer occupyNum;

    /**
     * 手工下单的确认数量
     */
    private Integer confirmNum;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getFirstDestGroupId() {
        return firstDestGroupId;
    }

    public void setFirstDestGroupId(Integer firstDestGroupId) {
        this.firstDestGroupId = firstDestGroupId;
    }

    public Integer getDestCategoryId() {
        return destCategoryId;
    }

    public void setDestCategoryId(Integer destCategoryId) {
        this.destCategoryId = destCategoryId;
    }

    public String getDestCategoryName() {
        return destCategoryName;
    }

    public void setDestCategoryName(String destCategoryName) {
        this.destCategoryName = destCategoryName;
    }

    public String getFirstDestGroupName() {
        return firstDestGroupName;
    }

    public void setFirstDestGroupName(String firstDestGroupName) {
        this.firstDestGroupName = firstDestGroupName;
    }

    public Integer getSecDestGroupId() {
        return secDestGroupId;
    }

    public void setSecDestGroupId(Integer secDestGroupId) {
        this.secDestGroupId = secDestGroupId;
    }

    public String getSecDestGroupName() {
        return secDestGroupName;
    }

    public void setSecDestGroupName(String secDestGroupName) {
        this.secDestGroupName = secDestGroupName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Date getDepartDate() {
        return departDate;
    }

    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getCustomerStaffId() {
        return customerStaffId;
    }

    public void setCustomerStaffId(Integer customerStaffId) {
        this.customerStaffId = customerStaffId;
    }

    public String getCustomerStaffName() {
        return customerStaffName;
    }

    public void setCustomerStaffName(String customerStaffName) {
        this.customerStaffName = customerStaffName;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
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

    public Integer getOperationStaffId() {
        return operationStaffId;
    }

    public void setOperationStaffId(Integer operationStaffId) {
        this.operationStaffId = operationStaffId;
    }

    public String getOperationStaffName() {
        return operationStaffName;
    }

    public void setOperationStaffName(String operationStaffName) {
        this.operationStaffName = operationStaffName;
    }

    public Integer getOperationManagerId() {
        return operationManagerId;
    }

    public void setOperationManagerId(Integer operationManagerId) {
        this.operationManagerId = operationManagerId;
    }

    public String getOperationManagerName() {
        return operationManagerName;
    }

    public void setOperationManagerName(String operationManagerName) {
        this.operationManagerName = operationManagerName;
    }

    public Integer getExtBatchId() {
        return extBatchId;
    }

    public void setExtBatchId(Integer extBatchId) {
        this.extBatchId = extBatchId;
    }

    public Integer getExtDbatchId() {
        return extDbatchId;
    }

    public void setExtDbatchId(Integer extDbatchId) {
        this.extDbatchId = extDbatchId;
    }

    public Integer getOccupyBatchId() {
        return occupyBatchId;
    }

    public void setOccupyBatchId(Integer occupyBatchId) {
        this.occupyBatchId = occupyBatchId;
    }

    public Integer getOutLibraryId() {
        return outLibraryId;
    }

    public void setOutLibraryId(Integer outLibraryId) {
        this.outLibraryId = outLibraryId;
    }

    public Integer getDepartureCityCode() {
        return departureCityCode;
    }

    public void setDepartureCityCode(Integer departureCityCode) {
        this.departureCityCode = departureCityCode;
    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public Integer getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(Integer continentCode) {
        this.continentCode = continentCode;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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

    public Integer getDestId() {
        return destId;
    }

    public void setDestId(Integer destId) {
        this.destId = destId;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public Integer getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(Integer productLineId) {
        this.productLineId = productLineId;
    }

    public Integer getDealOrderType() {
        return dealOrderType;
    }

    public void setDealOrderType(Integer dealOrderType) {
        this.dealOrderType = dealOrderType;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    public Integer getDealOrderNum() {
        return dealOrderNum;
    }

    public void setDealOrderNum(Integer dealOrderNum) {
        this.dealOrderNum = dealOrderNum;
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

    public String getSellChannelCode() {
        return sellChannelCode;
    }

    public void setSellChannelCode(String sellChannelCode) {
        this.sellChannelCode = sellChannelCode;
    }

    public Integer getOccupyNum() {
        return occupyNum;
    }

    public void setOccupyNum(Integer occupyNum) {
        this.occupyNum = occupyNum;
    }

    public Integer getConfirmNum() {
        return confirmNum;
    }

    public void setConfirmNum(Integer confirmNum) {
        this.confirmNum = confirmNum;
    }

}