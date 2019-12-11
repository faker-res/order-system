/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年8月5日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import com.tuniu.ord.vo.OrderIdIntervalVo;

/**
 * 【POS客服端】
 * 
 * @author zhairongping
 *
 */
public interface IOrderIdServiceClient {
    /**
     * 生产订单号
     * 
     * @param intervalTypeId
     *            系统编号
     * @return 订单号
     */
    Long getOrderNum(Integer intervalTypeId);

    /**
     * 刷新订单号区间
     * 
     * @param input
     */
    void refreshOrderIdInterval(OrderIdIntervalVo input);
}
