/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月1日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import com.tuniu.ord.vo.OrderSynInfo;

/**
 * @author zhairongping
 *
 */
public interface SCSApiService {

    /**
     * 根据销售单号初始化财务实例
     * 
     * @param orderId
     * @param orderSynInfo
     * @return
     */
    public OrderSynInfo initOrderSynInfo(Integer orderId, OrderSynInfo orderSynInfo);
}
