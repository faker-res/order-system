/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月16日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.createOrder;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 订单查询输入输出参数
 * 
 * @author zhairongping
 *
 */
public class ManualOrderQueryVo extends PageQueryVo implements ManualOrderQueryInter {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    private Integer manualOrderId;

    /**
     * 订单状态
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
     * 产品编号
     */
    private Integer productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 团期
     */
    private String tourDate;

    /**
     * 创建订单时间
     */
    private Date createOrderTime;

    /**
     * 请求url
     */
    private String manualOrderURL;

    /**
     * 输出的团期
     */
    private String departDate;

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
        this.startTime = startTime;
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
        this.endTime = endTime;
    }

    /**
     * @return the createOrderTime
     */
    public Date getCreateOrderTime() {
        return createOrderTime;
    }

    /**
     * @param createOrderTime
     *            the createOrderTime to set
     */
    public void setCreateOrderTime(Date createOrderTime) {
        this.createOrderTime = createOrderTime;
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
        this.tourDate = tourDate;
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
     * @return the departDate
     */
    public String getDepartDate() {
        return departDate;
    }

    /**
     * @param departDate
     *            the departDate to set
     */
    public void setDepartDate(Date departDate) {
        this.departDate = DateFormatUtils.format(departDate, "yyyy-MM-dd");
    }

}
