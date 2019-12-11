/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月8日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.tuniu.ord.service.OrderService;
import com.tuniu.ord.vo.createOrder.OrderBaseParamInputVo;

/**
 * 系统下单服务
 * 
 * @author zhairongping
 *
 */
@Service(value = "systemCreateOrderService")
public class SystemCreateOrderServiceImpl implements OrderService {

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.OrderService#validateOrder(com.tuniu.ord.vo.createOrder.OrderBaseParamInputVo)
     */
    @Override
    public Map<String, Object> validateOrder(OrderBaseParamInputVo input) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.OrderService#createOrder(com.tuniu.ord.vo.createOrder.OrderBaseParamInputVo)
     */
    @Override
    public Integer createOrder(OrderBaseParamInputVo input) {
        // TODO Auto-generated method stub
        return null;
    }

}
