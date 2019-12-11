/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月8日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.Map;

import com.tuniu.ord.vo.createOrder.OrderBaseParamInputVo;

/**
 * 下单服务
 * 
 * @author zhairongping
 *
 */
public interface OrderService {

    /**
     * 下单校验
     * 
     * @param input
     * @return
     */
    Map<String, Object> validateOrder(OrderBaseParamInputVo input);

    /**
     * 创建订单
     * 
     * @param input
     * @return
     */
    Integer createOrder(OrderBaseParamInputVo input);

}
