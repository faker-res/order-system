/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月22日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.vo.OrdSalesPriceRelationVo;
import com.tuniu.ord.vo.OrderBasicQueryVo;
import com.tuniu.ord.vo.OrderBasicStatisticsVo;
import com.tuniu.ord.vo.OrderRelationResultVo;

/**
 * @author zhairongping
 * 
 */
public interface OrdSalesOrderService {

    OrderBasicStatisticsVo getOrderBasicStatistics(OrderBasicQueryVo OrderBasicQueryinputVo);

    List<OrderRelationResultVo> getRelation(OrdSalesPriceRelationVo ordSalesPriceRelationVo);

    int countByRelation(OrdSalesPriceRelationVo ordSalesPriceRelationVo);

}
