/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月12日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * {
  "businessType": 1111,
  "isUnused": 0,
  "isRecovered": 0,
  "orderId": 100001,
  "start": 0,
  "limit": 300
}
 */
/**
 * @author zhairongping
 *
 */
public class StockOutQueryVo {
    private static final Integer DEFAULT_BUSINESS_TYPE = 1111;
    private static final Integer DEFAULT_IS_UNUSEED = 0;
    private static final Integer DEFAULT_IS_RECOVERED = 0;
    private static final Integer DEFAULT_START = 0;
    private static final Integer DEFAULT_LIMIT = 300;

    /**
     * 1110：D类资源 1111：D类产品
     */
    private Integer businessType = DEFAULT_BUSINESS_TYPE;
    /**
     * 0：有效 1：失效
     */
    private Integer isUnused = DEFAULT_IS_UNUSEED;
    /**
     * 0：未回库 1：已回库
     */
    private Integer isRecovered = DEFAULT_IS_RECOVERED;
    /**
     * D类销售单号
     */
    private Integer orderId;
    /**
     * 分页查询起始标识
     */
    private Integer start = DEFAULT_START;
    /**
     * 分页查询限制数量，最大300条数据
     */
    private Integer limit = DEFAULT_LIMIT;

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getIsUnused() {
        return isUnused;
    }

    public void setIsUnused(Integer isUnused) {
        this.isUnused = isUnused;
    }

    public Integer getIsRecovered() {
        return isRecovered;
    }

    public void setIsRecovered(Integer isRecovered) {
        this.isRecovered = isRecovered;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
