/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月22日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.vo.OrderDetailOutputVo;
import com.tuniu.ord.vo.OrderDetailQueryVo;
import com.tuniu.ord.vo.OrderHeadInfoVo;
import com.tuniu.ord.vo.OrderListOutputVo;
import com.tuniu.ord.vo.OrderOueryVo;

/**
 * @author zhairongping
 *
 */
public interface OrdDealOrderService {
    OrderHeadInfoVo getOrdDealOrdeByOrderId(Integer OrderId);

    List<OrderListOutputVo> getOrderListByPage(OrderOueryVo orderOueryVo);

    Integer count(OrderOueryVo orderOueryVo);

    OrderDetailOutputVo getOrderDetail(OrderDetailQueryVo orderDetailQueryVo);
}
