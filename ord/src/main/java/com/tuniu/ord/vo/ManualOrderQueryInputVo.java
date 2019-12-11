/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月30日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

import com.tuniu.ord.vo.createOrder.PageQueryVo;

/**
 * 人工订单查询条件
 * 
 * @author zhairongping
 *
 */
public class ManualOrderQueryInputVo extends PageQueryVo {

    /**
     * 人工订单
     */
    private Integer manualOrderId;

    /**
     * 订单状态(0:接单,10:需求确认,20:签约付款,30:确认,40:出团通知,50:出游归来)
     */
    private Integer statusCode;

    /**
     * 下单开始时间
     */
    private String startTime;

    /**
     * 下单结束时间
     */
    private String endTime;

    /**
     * 会员ID
     */
    private Integer memberId;

    /**
     * 产品编号集合
     */
    private List<Integer> productIds;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品负责人
     */
    private String productLeaderId;

    /**
     * 目的地大类ID
     */
    private Integer destCategoryId;

    /**
     * 一级目的地分组ID
     */
    private Integer firstDestGroupId;
    /**
     * 二级目的地分组ID
     */
    private Integer secDestGroupId;

    /**
     * 目的地
     */
    private Integer destId;

    /**
     * 团期开始时间
     */
    private String tourStartDate;

    /**
     * 团期结束时间
     */
    private String tourEndDate;

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
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(String startTime) {
        if (null != startTime && !"".equals(startTime)) {
            this.startTime = startTime.concat(" 00:00:00");
        }
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(String endTime) {
        if (null != endTime && !"".equals(endTime)) {
            this.endTime = endTime.concat(" 23:59:59");
        }
    }

    /**
     * @return the memberId
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     *            the memberId to set
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the productIds
     */
    public List<Integer> getProductIds() {
        return productIds;
    }

    /**
     * @param productIds
     *            the productIds to set
     */
    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
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

    /**
     * @return the productLeaderId
     */
    public String getProductLeaderId() {
        return productLeaderId;
    }

    /**
     * @param productLeaderId
     *            the productLeaderId to set
     */
    public void setProductLeaderId(String productLeaderId) {
        this.productLeaderId = productLeaderId;
    }

    /**
     * @return the destCategoryId
     */
    public Integer getDestCategoryId() {
        return destCategoryId;
    }

    /**
     * @param destCategoryId
     *            the destCategoryId to set
     */
    public void setDestCategoryId(Integer destCategoryId) {
        this.destCategoryId = destCategoryId;
    }

    /**
     * @return the firstDestGroupId
     */
    public Integer getFirstDestGroupId() {
        return firstDestGroupId;
    }

    /**
     * @param firstDestGroupId
     *            the firstDestGroupId to set
     */
    public void setFirstDestGroupId(Integer firstDestGroupId) {
        this.firstDestGroupId = firstDestGroupId;
    }

    /**
     * @return the secDestGroupId
     */
    public Integer getSecDestGroupId() {
        return secDestGroupId;
    }

    /**
     * @param secDestGroupId
     *            the secDestGroupId to set
     */
    public void setSecDestGroupId(Integer secDestGroupId) {
        this.secDestGroupId = secDestGroupId;
    }

    /**
     * @return the destId
     */
    public Integer getDestId() {
        return destId;
    }

    /**
     * @param destId
     *            the destId to set
     */
    public void setDestId(Integer destId) {
        this.destId = destId;
    }

    /**
     * @return the tourStartDate
     */
    public String getTourStartDate() {
        return tourStartDate;
    }

    /**
     * @param tourStartDate
     *            the tourStartDate to set
     */
    public void setTourStartDate(String tourStartDate) {
        if (null != tourStartDate && !"".equals(tourStartDate)) {
            this.tourStartDate = tourStartDate.concat(" 00:00:00");
        }
    }

    /**
     * @return the tourEndDate
     */
    public String getTourEndDate() {
        return tourEndDate;
    }

    /**
     * @param tourEndDate
     *            the tourEndDate to set
     */
    public void setTourEndDate(String tourEndDate) {
        if (null != tourEndDate && !"".equals(tourEndDate)) {
            this.tourEndDate = tourEndDate.concat(" 23:59:59");
        }
    }

}
