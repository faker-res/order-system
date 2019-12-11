/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月24日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.validator.ArgumentValidator;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.service.OrdSalesPriceService;
import com.tuniu.ord.vo.OrdSalesPriceVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author fangzhongwei
 * 
 */
@Controller
@RequestMapping("/rest/sales/order")
public class SalesOrderPriceController {

    @Resource
    private OrdSalesPriceService ordSalesPriceServiceImpl;

    @RequestMapping(path = "/list/query", method = { RequestMethod.GET })
    public void getSalesOrderList(OrdSalesPriceVo ordSalesPriceVo, HttpServletResponse response) {
        ArgumentValidator.isNotNull("ordSalesPriceVo", ordSalesPriceVo);

        List<OrdSalesOrder> ordSalesOrderList = ordSalesPriceServiceImpl.getOrdSalesPrice(ordSalesPriceVo);

        ResponseVo responseVo = new ResponseVo();
        if (CollectionUtils.isNotEmpty(ordSalesOrderList)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("count", Integer.valueOf(ordSalesPriceServiceImpl.count(ordSalesPriceVo)));
            map.put("rows", ordSalesOrderList);
            responseVo.setData(map);
        }

        RestUtil.write(response, responseVo);
    }

}
