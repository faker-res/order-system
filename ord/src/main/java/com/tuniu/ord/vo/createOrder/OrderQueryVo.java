/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.createOrder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单查询条件
 * 
 * @author zhairongping
 *
 */
public class OrderQueryVo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 切位单号
     */
    private Integer dOrderId;
    /**
     * 占位数量
     */
    private Integer number;
    /**
     * 下单备忘
     */
    private String remark;

    /**
     * 成人数
     */
    private Integer adultCount = new Integer(0);
    /**
     * 成人价
     */
    private BigDecimal adultPrice;
    /**
     * 儿童数
     */
    private Integer childCount = new Integer(0);

    /**
     * 儿童价
     */
    private BigDecimal childPrice;
    /**
     * 老人数
     */
    private Integer olderCount = new Integer(0);
    /**
     * 单房差数量
     */
    private Integer singleRoomCount = new Integer(0);
    /**
     * 单房差价格
     */
    private BigDecimal singleRoomPrice;

    /**
     * 切位方式(0:软切 1:硬切)
     */
    private Integer cutType;

    /**
     * @return the dOrderId
     */
    public Integer getdOrderId() {
        return dOrderId;
    }

    /**
     * @param dOrderId
     *            the dOrderId to set
     */
    public void setdOrderId(Integer dOrderId) {
        this.dOrderId = dOrderId;
    }

    /**
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number
     *            the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * @return the adultPrice
     */
    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    /**
     * @param adultPrice
     *            the adultPrice to set
     */
    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
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
     * @return the childPrice
     */
    public BigDecimal getChildPrice() {
        return childPrice;
    }

    /**
     * @param childPrice
     *            the childPrice to set
     */
    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    /**
     * @return the singleRoomCount
     */
    public Integer getSingleRoomCount() {
        return singleRoomCount;
    }

    /**
     * @param singleRoomCount
     *            the singleRoomCount to set
     */
    public void setSingleRoomCount(Integer singleRoomCount) {
        this.singleRoomCount = singleRoomCount;
    }

    /**
     * @return the singleRoomPrice
     */
    public BigDecimal getSingleRoomPrice() {
        return singleRoomPrice;
    }

    /**
     * @param singleRoomPrice
     *            the singleRoomPrice to set
     */
    public void setSingleRoomPrice(BigDecimal singleRoomPrice) {
        this.singleRoomPrice = singleRoomPrice;
    }

    /**
     * @return the cutType
     */
    public Integer getCutType() {
        return cutType;
    }

    /**
     * @param cutType
     *            the cutType to set
     */
    public void setCutType(Integer cutType) {
        this.cutType = cutType;
    }

    /**
     * @return the olderCount
     */
    public Integer getOlderCount() {
        return olderCount;
    }

    /**
     * @param olderCount
     *            the olderCount to set
     */
    public void setOlderCount(Integer olderCount) {
        this.olderCount = olderCount;
    }

}
