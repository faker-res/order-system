/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月12日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import com.tuniu.ord.common.constant.Constants;

/**
 * {
  "clientFlag": "D_ORD",
  "occupyObjId": 70001298,
  "departsDate": null,
  "sellResType": null,
  "sellResId": null,
  "businessType": 1111,
  "start": 0,
  "limit": 300
}
 */
/**
 * @author zhairongping
 *
 */
public class StockOccupyQueryVo {
    private static final Integer DEFAULT_BUSINESS_TYPE = 1111;
    private static final Integer DEFAULT_START = 0;
    private static final Integer DEFAULT_LIMIT = 300;

    private String clientFlag = Constants.CLIENT_FLAG;
    /**
     * D类切位单
     */
    private Integer occupyObjId;
    private String departsDate;
    private Integer sellResType;
    private Integer sellResId;
    private Integer businessType = DEFAULT_BUSINESS_TYPE;
    private Integer start = DEFAULT_START;
    private Integer limit = DEFAULT_LIMIT;

    public String getClientFlag() {
        return clientFlag;
    }

    public void setClientFlag(String clientFlag) {
        this.clientFlag = clientFlag;
    }

    public Integer getOccupyObjId() {
        return occupyObjId;
    }

    public void setOccupyObjId(Integer occupyObjId) {
        this.occupyObjId = occupyObjId;
    }

    public String getDepartsDate() {
        return departsDate;
    }

    public void setDepartsDate(String departsDate) {
        this.departsDate = departsDate;
    }

    public Integer getSellResType() {
        return sellResType;
    }

    public void setSellResType(Integer sellResType) {
        this.sellResType = sellResType;
    }

    public Integer getSellResId() {
        return sellResId;
    }

    public void setSellResId(Integer sellResId) {
        this.sellResId = sellResId;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
