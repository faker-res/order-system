/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年7月1日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.external;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhairongping
 * 
 */
public class QueryGroupOutputVo {
    private Integer productId;
    private String departDate;
    private Integer tourId;
    private String tourName;
    private BigDecimal adultCost = new BigDecimal(0);
    private BigDecimal childCost = new BigDecimal(0);
    private BigDecimal shareRoomPrice = new BigDecimal(0);
    private Integer tourExpectNum;
    // 组团系统需求增加状态字段
    private Integer tourStatus;
    private Integer departDateStatus;
    private int releaseDay;
    private int releaseOclock;
    private int releaseMinute;
    private Date releaseTime;
    private int deadlineDay;
    private int deadlineOclock;
    private int deadlineMinute;
    private Date deadlineTime;
    private int isSupportChild;
    // private List<UpgradePriceVo> upgradePrice;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public BigDecimal getAdultCost() {
        return adultCost;
    }

    public void setAdultCost(BigDecimal adultCost) {
        this.adultCost = adultCost;
    }

    public BigDecimal getChildCost() {
        return childCost;
    }

    public void setChildCost(BigDecimal childCost) {
        this.childCost = childCost;
    }

    public BigDecimal getShareRoomPrice() {
        return shareRoomPrice;
    }

    public void setShareRoomPrice(BigDecimal shareRoomPrice) {
        this.shareRoomPrice = shareRoomPrice;
    }

    public Integer getTourExpectNum() {
        return tourExpectNum;
    }

    public void setTourExpectNum(Integer tourExpectNum) {
        this.tourExpectNum = tourExpectNum;
    }

    public Integer getTourStatus() {
        return tourStatus;
    }

    public void setTourStatus(Integer tourStatus) {
        this.tourStatus = tourStatus;
    }

    public int getReleaseDay() {
        return releaseDay;
    }

    public void setReleaseDay(int releaseDay) {
        this.releaseDay = releaseDay;
    }

    public int getReleaseOclock() {
        return releaseOclock;
    }

    public void setReleaseOclock(int releaseOclock) {
        this.releaseOclock = releaseOclock;
    }

    public int getReleaseMinute() {
        return releaseMinute;
    }

    public void setReleaseMinute(int releaseMinute) {
        this.releaseMinute = releaseMinute;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getDeadlineDay() {
        return deadlineDay;
    }

    public void setDeadlineDay(int deadlineDay) {
        this.deadlineDay = deadlineDay;
    }

    public int getDeadlineOclock() {
        return deadlineOclock;
    }

    public void setDeadlineOclock(int deadlineOclock) {
        this.deadlineOclock = deadlineOclock;
    }

    public int getDeadlineMinute() {
        return deadlineMinute;
    }

    public void setDeadlineMinute(int deadlineMinute) {
        this.deadlineMinute = deadlineMinute;
    }

    public Date getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(Date deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public int getIsSupportChild() {
        return isSupportChild;
    }

    public void setIsSupportChild(int isSupportChild) {
        this.isSupportChild = isSupportChild;
    }

    // public List<UpgradePriceVo> getUpgradePrice() {
    // return upgradePrice;
    // }
    //
    // public void setUpgradePrice(List<UpgradePriceVo> upgradePrice) {
    // this.upgradePrice = upgradePrice;
    // }

    public Integer getDepartDateStatus() {
        return departDateStatus;
    }

    public void setDepartDateStatus(Integer departDateStatus) {
        this.departDateStatus = departDateStatus;
    }

}
