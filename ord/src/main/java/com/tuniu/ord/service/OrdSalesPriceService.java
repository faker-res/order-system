/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.vo.OrdSalesPriceVo;

/**
 * @author fangzhongwei
 * 
 */
public interface OrdSalesPriceService {

    List<OrdSalesOrder> getOrdSalesPrice(OrdSalesPriceVo ordSalesPriceVo);

    int count(OrdSalesPriceVo ordSalesPriceVo);

}
