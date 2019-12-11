/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月30日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.io.Serializable;

/**
 * 人工订单查询结果
 * 
 * @author zhairongping
 *
 */
public class ManualOrderQueryOutputVo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 人工订单
     */
    private Integer manualOrderId;
    /**
     * 订单状态
     */
    private Integer statusCode;
    /**
     * 下单时间
     */
    private String createOrderTime;
    /**
     * 订单地址
     */
    private String manualOrderURL;
    /**
     * 产品编号
     */
    private Integer productId;
    /**
     * 产品名称
     */
    private String productName;
    // /**
    // * 产品负责人
    // */
    // private Integer productLeaderId;
    /**
     * 产品负责人名
     */
    private String productLeaderName;
    /**
     * 团号
     */
    private Integer tourId;
    /**
     * 团名称
     */
    private String tourName;
    /**
     * 数量
     */
    private Integer tourNum;
    /**
     * 成人数
     */
    private Integer adultCount;
    /**
     * 儿童数
     */
    private Integer childCount;

    /**
     * 团期
     */
    private String tourDate;

    /**
     * @return the tourDate
     */
    public String getTourDate() {
        return tourDate;
    }

    /**
     * @param tourDate
     *            the tourDate to set
     */
    public void setTourDate(String tourDate) {
        if (tourDate != null && !"".equals(tourDate)) {
            this.tourDate = tourDate.substring(0, 10);
        }
    }

    /**
     * @return the adultCount
     */
    public Integer getAdultCount() {
        return adultCount;
    }

    /**
     * @param adultCount
     *            the adultCount to set
     */
    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    /**
     * @return the childCount
     */
    public Integer getChildCount() {
        return childCount;
    }

    /**
     * @param childCount
     *            the childCount to set
     */
    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    /**
     * @return the manualOrderId
     */
    public Integer getManualOrderId() {
        return manualOrderId;
    }

    /**
     * @param manualOrderId
     *            the manualOrderId to set
     */
    public void setManualOrderId(Integer manualOrderId) {
        this.manualOrderId = manualOrderId;
    }

    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode
     *            the statusCode to set
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the createOrderTime
     */
    public String getCreateOrderTime() {
        return createOrderTime;
    }

    /**
     * @param createOrderTime
     *            the createOrderTime to set
     */
    public void setCreateOrderTime(String createOrderTime) {
        if (createOrderTime != null && !"".equals(createOrderTime)) {
            this.createOrderTime = createOrderTime.substring(0, 19);
        }
    }

    /**
     * @return the manualOrderURL
     */
    public String getManualOrderURL() {
        return manualOrderURL;
    }

    /**
     * @param manualOrderURL
     *            the manualOrderURL to set
     */
    public void setManualOrderURL(String manualOrderURL) {
        this.manualOrderURL = manualOrderURL;
    }

    /**
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
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

    // /**
    // * @return the productLeaderId
    // */
    // public Integer getProductLeaderId() {
    // return productLeaderId;
    // }
    //
    // /**
    // * @param productLeaderId
    // * the productLeaderId to set
    // */
    // public void setProductLeaderId(Integer productLeaderId) {
    // this.productLeaderId = productLeaderId;
    // }

    /**
     * @return the productLeaderName
     */
    public String getProductLeaderName() {
        return productLeaderName;
    }

    /**
     * @param productLeaderName
     *            the productLeaderName to set
     */
    public void setProductLeaderName(String productLeaderName) {
        this.productLeaderName = productLeaderName;
    }

    /**
     * @return the tourId
     */
    public Integer getTourId() {
        return tourId;
    }

    /**
     * @param tourId
     *            the tourId to set
     */
    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    /**
     * @return the tourName
     */
    public String getTourName() {
        return tourName;
    }

    /**
     * @param tourName
     *            the tourName to set
     */
    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    /**
     * @return the tourNum
     */
    public Integer getTourNum() {
        return tourNum;
    }

    /**
     * @param tourNum
     *            the tourNum to set
     */
    public void setTourNum(Integer tourNum) {
        this.tourNum = tourNum;
    }

}
