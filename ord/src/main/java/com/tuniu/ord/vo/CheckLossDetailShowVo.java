package com.tuniu.ord.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CheckLossDetailShowVo implements Serializable {
    private static final long serialVersionUID = -5807920148765391294L;

    /**
     * 出游日期
     */
    private Date tourDate;

    /**
     * 产品id
     */
    private int productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * A类产品id
     */
    private int extProductId;

    /**
     * A订单
     */
    private int extOrderId;

    /**
     * 出游需求
     */
    private String tourRequirement;

    /**
     * 出游人信息
     */
    private String tourInfo;

    /**
     * 核损单状态
     */
    private String status;

    /**
     * 币种
     */
    private String currencyType;

    /**
     * 核损备注
     */
    private String remark;

    /**
     * 核损有效期
     */
    private String expireTime;

    /**
     * 核损单类型
     */
    private Integer checkLossType;

    /**
     * A批次列表
     */
    private List<Map<String, Object>> roundList;

    public Date getTourDate() {
        return tourDate;
    }

    public void setTourDate(Date tourDate) {
        this.tourDate = tourDate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getExtProductId() {
        return extProductId;
    }

    public void setExtProductId(int extProductId) {
        this.extProductId = extProductId;
    }

    public int getExtOrderId() {
        return extOrderId;
    }

    public void setExtOrderId(int extOrderId) {
        this.extOrderId = extOrderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getTourRequirement() {
        return tourRequirement;
    }

    public void setTourRequirement(String tourRequirement) {
        this.tourRequirement = tourRequirement;
    }

    public String getTourInfo() {
        return tourInfo;
    }

    public void setTourInfo(String tourInfo) {
        this.tourInfo = tourInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Map<String, Object>> getRoundList() {
        return roundList;
    }

    public void setRoundList(List<Map<String, Object>> roundList) {
        this.roundList = roundList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getCheckLossType() {
        return checkLossType;
    }

    public void setCheckLossType(Integer checkLossType) {
        this.checkLossType = checkLossType;
    }
}
