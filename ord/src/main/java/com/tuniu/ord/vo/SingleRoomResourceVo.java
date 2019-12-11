/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月16日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 *
 */
public class SingleRoomResourceVo {
    private Integer resourceId;
    private String resourceName;
    private Integer adultCnt;
    private String occupyTime;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getAdultCnt() {
        return adultCnt;
    }

    public void setAdultCnt(Integer adultCnt) {
        this.adultCnt = adultCnt;
    }

    public String getOccupyTime() {
        return occupyTime;
    }

    public void setOccupyTime(String occupyTime) {
        this.occupyTime = occupyTime;
    }

}
