/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.service.OrdSalesPriceService;
import com.tuniu.ord.vo.OrdSalesPriceVo;

/**
 * @author fangzhongwei
 * 
 */
@Service
public class OrdSalesPriceServiceImpl implements OrdSalesPriceService {

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    @Override
    public List<OrdSalesOrder> getOrdSalesPrice(OrdSalesPriceVo ordSalesPriceVo) {
        return ordSalesOrderMapper.selectByFields(ordSalesPriceVo);
    }

    @Override
    public int count(OrdSalesPriceVo ordSalesPriceVo) {
        return ordSalesOrderMapper.countByFields(ordSalesPriceVo);
    }

}
