/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Wed May 25 09:56:13 CST 2016
 * Description:
 */
package com.tuniu.ord.domain;

import java.util.Date;

/**
 * CheckLoss check_loss
 */
public class CheckLoss extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 外部的售卖订单号 check_loss.sell_order_id
     */
    private Integer sellOrderId;

    /**
     * 需求ID check_loss.requirement_id
     */
    private Integer requirementId;

    /**
     * 产品编号 check_loss.product_id
     */
    private Integer productId;

    /**
     * 名称 check_loss.product_name
     */
    private String productName;

    /**
     * 团Id check_loss.group_id
     */
    private Integer groupId;

    /**
     * 团Name check_loss.group_name
     */
    private String groupName;

    /**
     * 团期 check_loss.tour_date
     */
    private Date tourDate;

    /**
     * 渠道code check_loss.sell_channel
     */
    private String sellChannel;

    /**
     * 渠道数量 check_loss.channel_count
     */
    private Integer channelCount;

    /**
     * 负责人ID check_loss.user_id
     */
    private Integer userId;

    /**
     * 负责人Name check_loss.name
     */
    private String name;

    /**
     * 核损单状态code check_loss.status
     */
    private String status;

    /**
     * 反馈时间 check_loss.resp_time
     */
    private Date respTime;

    /**
     * 分配给供应商的唯一身份标识 check_loss.api_key
     */
    private String apiKey;

    /**
     * 时间戳 check_loss.timestamp
     */
    private String timestamp;

    /**
     * 请求签名，生成规则参见签名机制 check_loss.sign
     */
    private String sign;

    /**
     * 
     * check_loss.session_id
     */
    private Integer sessionId;

    /**
     * 核损单类型 0 人工核损 1 自动核损
     */
    private Integer checkLossType;

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    /**
     * @return check_loss.sell_order_id
     */
    public Integer getSellOrderId() {
        return sellOrderId;
    }

    /**
     * @param Integer
     *            sellOrderId (check_loss.sell_order_id )
     */
    public void setSellOrderId(Integer sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    /**
     * @return check_loss.product_id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param Integer
     *            productId (check_loss.product_id )
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return check_loss.product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param String
     *            productName (check_loss.product_name )
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     * @return check_loss.group_id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param Integer
     *            groupId (check_loss.group_id )
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * @return check_loss.group_name
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param String
     *            groupName (check_loss.group_name )
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * @return check_loss.tour_date
     */
    public Date getTourDate() {
        return tourDate;
    }

    /**
     * @param Date
     *            tourDate (check_loss.tour_date )
     */
    public void setTourDate(Date tourDate) {
        this.tourDate = tourDate;
    }

    /**
     * @return check_loss.sell_channel
     */
    public String getSellChannel() {
        return sellChannel;
    }

    /**
     * @param String
     *            sellChannel (check_loss.sell_channel )
     */
    public void setSellChannel(String sellChannel) {
        this.sellChannel = sellChannel == null ? null : sellChannel.trim();
    }

    /**
     * @return check_loss.channel_count
     */
    public Integer getChannelCount() {
        return channelCount;
    }

    /**
     * @param Integer
     *            channelCount (check_loss.channel_count )
     */
    public void setChannelCount(Integer channelCount) {
        this.channelCount = channelCount;
    }

    /**
     * @return check_loss.user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param Integer
     *            userId (check_loss.user_id )
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return check_loss.name
     */
    public String getName() {
        return name;
    }

    /**
     * @param String
     *            name (check_loss.name )
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return check_loss.status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param String
     *            status (check_loss.status )
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return check_loss.resp_time
     */
    public Date getRespTime() {
        return respTime;
    }

    /**
     * @param Date
     *            respTime (check_loss.resp_time )
     */
    public void setRespTime(Date respTime) {
        this.respTime = respTime;
    }

    /**
     * @return check_loss.api_key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param String
     *            apiKey (check_loss.api_key )
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey == null ? null : apiKey.trim();
    }

    /**
     * @return check_loss.timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param String
     *            timestamp (check_loss.timestamp )
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp == null ? null : timestamp.trim();
    }

    /**
     * @return check_loss.sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * @param String
     *            sign (check_loss.sign )
     */
    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    /**
     * @return check_loss.session_id
     */
    public Integer getSessionId() {
        return sessionId;
    }

    /**
     * @param Integer
     *            sessionId (check_loss.session_id )
     */
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getCheckLossType() {
        return checkLossType;
    }

    public void setCheckLossType(Integer checkLossType) {
        this.checkLossType = checkLossType;
    }

}