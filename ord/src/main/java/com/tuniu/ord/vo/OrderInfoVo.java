/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月16日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * @author zhairongping
 *
 */
public class OrderInfoVo {
    /**
     * D的销售单号
     */
    private Integer orderId;
    /**
     * 外部的渠道订单号
     */
    private Integer channelOrderId;
    private String channelName;
    private Integer tourId;
    private Integer childCnt;
    private Integer adultCnt;
    private String productId;
    private String signDate;
    private List<SingleRoomResourceVo> singleRoomResourceList;
    private List<TourResourceVo> tourResourceList;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getChildCnt() {
        return childCnt;
    }

    public void setChildCnt(Integer childCnt) {
        this.childCnt = childCnt;
    }

    public Integer getAdultCnt() {
        return adultCnt;
    }

    public void setAdultCnt(Integer adultCnt) {
        this.adultCnt = adultCnt;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public List<SingleRoomResourceVo> getSingleRoomResourceList() {
        return singleRoomResourceList;
    }

    public void setSingleRoomResourceList(List<SingleRoomResourceVo> singleRoomResourceList) {
        this.singleRoomResourceList = singleRoomResourceList;
    }

    public List<TourResourceVo> getTourResourceList() {
        return tourResourceList;
    }

    public void setTourResourceList(List<TourResourceVo> tourResourceList) {
        this.tourResourceList = tourResourceList;
    }

    public Integer getChannelOrderId() {
        return channelOrderId;
    }

    public void setChannelOrderId(Integer channelOrderId) {
        this.channelOrderId = channelOrderId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

}
