/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.Logging.Log4jLogger;
import com.tuniu.ord.core.Logging.LogFactory;
import com.tuniu.ord.service.OrdDealOrderService;
import com.tuniu.ord.service.OrdSalesOrderService;
import com.tuniu.ord.vo.OrdSalesPriceRelationVo;
import com.tuniu.ord.vo.OrderDetailOutputVo;
import com.tuniu.ord.vo.OrderDetailQueryVo;
import com.tuniu.ord.vo.OrderListOutputVo;
import com.tuniu.ord.vo.OrderOueryVo;
import com.tuniu.ord.vo.OrderRelationResultVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author zhairongping
 * 
 */
@Controller
@RequestMapping("/rest/order")
public class OrderController {
    private static Log4jLogger logger = LogFactory.getLogger(OrderController.class);

    @Resource
    private OrdDealOrderService ordDealOrderServiceImpl;

    @Resource
    private OrdSalesOrderService ordSalesOrderServiceImpl;

    /**
     * 订单详情接口
     * 
     * @param request
     * @param response
     * @param orderDetailQueryVo
     */
    @RequestMapping(value = "/getOrderDetail", method = RequestMethod.GET)
    public void getOrderDetail(HttpServletRequest request, HttpServletResponse response, OrderDetailQueryVo orderDetailQueryVo) {
        ResponseVo responseVo = new ResponseVo();
        OrderDetailOutputVo orderDetailOutputVo = new OrderDetailOutputVo();
        orderDetailOutputVo = ordDealOrderServiceImpl.getOrderDetail(orderDetailQueryVo);
        responseVo.setData(orderDetailOutputVo);
        RestUtil.write(response, responseVo);
    }

    /**
     * 
     * 订单搜索接口
     * 
     * @param request
     * @param response
     * @param orderOueryInputVo
     */
    @RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
    public void getOrderList(HttpServletRequest request, HttpServletResponse response, OrderOueryVo orderOueryInputVo) {
        logger.debug(Log4jLogger.SYSTEM_LOG, "OrderController-getOrderList-input=====" + JsonUtil.toString(orderOueryInputVo));
        ResponseVo responseVo = new ResponseVo();
        Integer count = ordDealOrderServiceImpl.count(orderOueryInputVo);
        List<OrderListOutputVo> rows = ordDealOrderServiceImpl.getOrderListByPage(orderOueryInputVo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("rows", rows);
        logger.debug(Log4jLogger.SYSTEM_LOG, "OrderController-getOrderList-output=====" + JsonUtil.toString(map));
        responseVo.setData(map);
        RestUtil.write(response, responseVo);
    }

    @RequestMapping(value = "/relation", method = RequestMethod.GET)
    public void getOrderRelation(HttpServletResponse response, OrdSalesPriceRelationVo ordSalesPriceRelationVo) {
        ResponseVo responseVo = new ResponseVo();

        List<OrderRelationResultVo> rows = ordSalesOrderServiceImpl.getRelation(ordSalesPriceRelationVo);
        int count = ordSalesOrderServiceImpl.countByRelation(ordSalesPriceRelationVo);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", Integer.valueOf(count));
        map.put("rows", rows);
        responseVo.setData(map);
        RestUtil.write(response, responseVo);
    }

}
