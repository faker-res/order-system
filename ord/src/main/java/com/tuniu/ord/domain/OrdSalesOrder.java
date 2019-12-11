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
 * OrdSalesOrder ord_sales_order
 */
public class OrdSalesOrder extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * D订单号 ord_sales_order.order_id
     */
    private Integer orderId;

    /**
     * 销售订单号 ord_sales_order.ext_order_id
     */
    private Integer extOrderId;

    /**
     * 需求ID ord_sales_order.requirement_id
     */
    private Integer requirementId;

    /**
     * 销售单对应的产品 ord_sales_order.ext_product_id
     */
    private Integer extProductId;

    /**
     * 销售单对应的产品名称 ord_sales_order.ext_product_name
     */
    private String extProductName;

    /**
     * 备注 ord_sales_order.remark
     */
    private String remark;

    /**
     * 总价 ord_sales_order.total_price
     */
    private BigDecimal totalPrice;

    /**
     * 实收 ord_sales_order.pay_amount
     */
    private BigDecimal payAmount;

    /**
     * 应收 ord_sales_order.receivable_amount
     */
    private BigDecimal receivableAmount;

    /**
     * 销售订单状态 ord_sales_order.status_code
     */
    private String statusCode;

    /**
     * 分配给供应商的唯一身份标识 ord_sales_order.api_key
     */
    private String apiKey;

    /**
     * 请求签名，生成规则参见签名机制 ord_sales_order.sign
     */
    private String sign;

    /**
     * 时间戳 ord_sales_order.timestamp
     */
    private String timestamp;

    /**
     * 途牛流水号 ord_sales_order.tuniu_serial_id
     */
    private Integer tuniuSerialId;

    /**
     * 会话id ord_sales_order.session_id
     */
    private Integer sessionId;

    /**
     * 币种 ord_sales_order.cost_currency_type
     */
    private String costCurrencyType;

    /**
     * 团期 ord_sales_order.start_date
     */
    private Date startDate;

    /**
     * 售卖渠道 ord_sales_order.sale_channel
     */
    private String saleChannel;

    /**
     * 成人数 ord_sales_order.adult_count
     */
    private Integer adultCount;

    /**
     * 儿童数 ord_sales_order.child_count
     */
    private Integer childCount;

    /**
     * 成人价 ord_sales_order.adult_price
     */
    private BigDecimal adultPrice;

    /**
     * 儿童价 ord_sales_order.child_price
     */
    private BigDecimal childPrice;

    /**
     * 单房差数量 ord_sales_order.room_add_num
     */
    private Integer roomAddNum;

    /**
     * 单房差 ord_sales_order.room_add_price
     */
    private BigDecimal roomAddPrice;

    /**
     * D出库单id ord_sales_order.ext_order_batch_id
     */
    private Integer extOrderBatchId;

    private Long resourceId;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getExtOrderId() {
        return extOrderId;
    }

    public void setExtOrderId(Integer extOrderId) {
        this.extOrderId = extOrderId;
    }

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public Integer getExtProductId() {
        return extProductId;
    }

    public void setExtProductId(Integer extProductId) {
        this.extProductId = extProductId;
    }

    public String getExtProductName() {
        return extProductName;
    }

    public void setExtProductName(String extProductName) {
        this.extProductName = extProductName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTuniuSerialId() {
        return tuniuSerialId;
    }

    public void setTuniuSerialId(Integer tuniuSerialId) {
        this.tuniuSerialId = tuniuSerialId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getCostCurrencyType() {
        return costCurrencyType;
    }

    public void setCostCurrencyType(String costCurrencyType) {
        this.costCurrencyType = costCurrencyType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getSaleChannel() {
        return saleChannel;
    }

    public void setSaleChannel(String saleChannel) {
        this.saleChannel = saleChannel;
    }

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
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

    public Integer getRoomAddNum() {
        return roomAddNum;
    }

    public void setRoomAddNum(Integer roomAddNum) {
        this.roomAddNum = roomAddNum;
    }

    public BigDecimal getRoomAddPrice() {
        return roomAddPrice;
    }

    public void setRoomAddPrice(BigDecimal roomAddPrice) {
        this.roomAddPrice = roomAddPrice;
    }

    public Integer getExtOrderBatchId() {
        return extOrderBatchId;
    }

    public void setExtOrderBatchId(Integer extOrderBatchId) {
        this.extOrderBatchId = extOrderBatchId;
    }

}