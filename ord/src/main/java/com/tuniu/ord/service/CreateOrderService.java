/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月7日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;
import java.util.Map;

import com.tuniu.ord.enums.DMSServiceEnum;
import com.tuniu.ord.enums.OrderModeEnum;
import com.tuniu.ord.vo.ManualOrderQueryInputVo;
import com.tuniu.ord.vo.ManualOrderQueryOutputVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.createOrder.DMSQueryBaseInputVo;
import com.tuniu.ord.vo.createOrder.ManualOrderQueryInter;
import com.tuniu.ord.vo.createOrder.OrderBaseParamInputVo;

/**
 * @author zhairongping
 *
 */
public interface CreateOrderService {
    /**
     * 下单校验
     * 
     * @param input
     * @param orderMode
     * @return
     */
    Map<String, Object> validateOrder(OrderBaseParamInputVo input, OrderModeEnum orderMode);

    /**
     * 创建订单
     * 
     * @param input
     * @param orderMode
     * @return
     */
    Integer createOrder(OrderBaseParamInputVo input, OrderModeEnum orderMode);

    /**
     * 会员服务
     * 
     * @param input
     * @param DMSService
     * @return
     */
    ResponseVo provideDMSService(DMSQueryBaseInputVo input, DMSServiceEnum DMSService);

    /**
     * 查询订单列表
     * 
     * @param input
     * @return
     */
    List<ManualOrderQueryInter> getManualOrderList(ManualOrderQueryInter input);

    /**
     * 统计订单数量
     * 
     * @param input
     * @return
     */
    Integer countManualOrderList(ManualOrderQueryInter input);

    /**
     * 统计订单数量
     * 
     * @param input
     * @return
     */
    Integer countNewManualOrder(ManualOrderQueryInputVo input);

    /**
     * 查询订单列表
     * 
     * @param input
     * @return
     */
    List<ManualOrderQueryOutputVo> getNewManualOrder(ManualOrderQueryInputVo input);

}
