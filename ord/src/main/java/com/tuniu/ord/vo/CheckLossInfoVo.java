package com.tuniu.ord.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CheckLossInfoVo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3278086705382451602L;

    /**
     * D类切位订单号
     */
    private Integer orderId;

    /**
     * 销售订单表主键
     */
    private Integer sellOrderId;

    /**
     * 成人数
     */
    private Integer adultCount;

    /**
     * 儿童数
     */
    private Integer childCount;

    /**
     * 成人价
     */
    private BigDecimal adultPrice;

    /**
     * 儿童价
     */
    private BigDecimal childPrice;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 产品Id
     */
    private Integer productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 团Id
     */
    private Integer groupId;

    /**
     * 团名称
     */
    private String groupName;

    /**
     * 团期
     */
    private Date tourDate;

    /**
     * 负责人Id
     */
    private Integer userId;

    /**
     * 负责人姓名
     */
    private String name;

    /**
     * 售卖渠道
     */
    private String saleChannel;

    /**
     * A资源批次号
     */
    private Integer extBatchId;

    public Integer getExtBatchId() {
        return extBatchId;
    }

    public void setExtBatchId(Integer extBatchId) {
        this.extBatchId = extBatchId;
    }

    public String getSaleChannel() {
        return saleChannel;
    }

    public void setSaleChannel(String saleChannel) {
        this.saleChannel = saleChannel;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(Integer sellOrderId) {
        this.sellOrderId = sellOrderId;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Date getTourDate() {
        return tourDate;
    }

    public void setTourDate(Date tourDate) {
        this.tourDate = tourDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
