/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年8月5日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 *
 */
public class OrderIdIntervalVo {
    /**
     * 客户端订单号容量警戒值
     */
    private Integer cordon;
    /**
     * 系统编号
     */
    private Integer intervalTypeId;

    public Integer getCordon() {
        return cordon;
    }

    public void setCordon(Integer cordon) {
        this.cordon = cordon;
    }

    public Integer getIntervalTypeId() {
        return intervalTypeId;
    }

    public void setIntervalTypeId(Integer intervalTypeId) {
        this.intervalTypeId = intervalTypeId;
    }

}
