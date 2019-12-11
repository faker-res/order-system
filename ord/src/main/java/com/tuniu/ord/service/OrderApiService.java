/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月16日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import com.tuniu.ord.vo.OrderInfoQueryInputVo;
import com.tuniu.ord.vo.OrderInfoQueryOutputVo;

/**
 * @author zhairongping
 *
 */
public interface OrderApiService {
    /**
     * 根据对客订单id查询订单详情接口
     * 
     * @param input
     * @return
     */
    public OrderInfoQueryOutputVo getOrdInfoByIds(OrderInfoQueryInputVo input);
}
