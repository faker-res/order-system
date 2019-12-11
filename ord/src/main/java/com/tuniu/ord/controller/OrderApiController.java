/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月16日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.service.IOrderIdServiceClient;
import com.tuniu.ord.service.OrderApiService;
import com.tuniu.ord.vo.OrderInfoQueryInputVo;
import com.tuniu.ord.vo.OrderInfoQueryOutputVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 【对外提供查询订单接口】
 * 
 * @author zhairongping
 *
 */
@Controller
@RequestMapping("/api/order")
public class OrderApiController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderApiController.class);

    @Resource
    private OrderApiService orderApiServiceImpl;

    @Resource
    private IOrderIdServiceClient iOrderIdServiceImplClient;

    /**
     * 根据对客订单id查询订单详情接口
     * 
     * @param input
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getOrdInfoByIds", method = RequestMethod.GET)
    public void getOrdInfoByIds(OrderInfoQueryInputVo input, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();
        LOG.info("OrderApiController-getOrdInfoByIds-input====" + JsonUtil.toString(input));
        OrderInfoQueryOutputVo orderInfoQueryOutputVo = orderApiServiceImpl.getOrdInfoByIds(input);
        LOG.info("OrderApiController-getOrdInfoByIds-output====" + JsonUtil.toString(orderInfoQueryOutputVo));
        responseVo.setData(orderInfoQueryOutputVo);
        RestUtil.write(response, responseVo);
    }

    /**
     * 生产订单号
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/create-order-id", method = RequestMethod.POST)
    public void createOrderId(HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();
        Long orderId = iOrderIdServiceImplClient.getOrderNum(Constants.INTERVAL_TYPE_ID);
        responseVo.setData(orderId);
        LOG.info("【提供给领队的订单号】===" + orderId);
        RestUtil.write(response, responseVo);
    }

}
