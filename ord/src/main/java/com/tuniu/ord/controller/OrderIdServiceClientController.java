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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.Logging.Log4jLogger;
import com.tuniu.ord.core.Logging.LogFactory;
import com.tuniu.ord.service.IOrderIdServiceClient;
import com.tuniu.ord.vo.OrderIdIntervalVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 【POS客服端】
 * 
 * @author zhairongping
 *
 */
@Controller
@RequestMapping("/api/order-id-service")
public class OrderIdServiceClientController {
    private static Log4jLogger logger = LogFactory.getLogger(OrderIdServiceClientController.class);

    @Resource
    private IOrderIdServiceClient iOrderIdServiceImplClient;

    /**
     * 生产订单号
     * 
     * @param input
     * @param request
     * @param response
     */
    @RequestMapping(value = "/get-order-num", method = RequestMethod.GET)
    public void getOrderNum(HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();
        Long orderId = iOrderIdServiceImplClient.getOrderNum(Constants.INTERVAL_TYPE_ID);
        responseVo.setData(orderId);
        logger.debug(Log4jLogger.SYSTEM_LOG, "OrderIdServiceClientController-getOrderNum-生产订单号====" + orderId);
        RestUtil.write(response, responseVo);
    }

    /**
     * 刷新订单号区间
     * 
     * @param input
     * @param request
     * @param response
     */
    @RequestMapping(value = "/refresh-order-id-interval", method = RequestMethod.POST)
    public void refreshOrderIdInterval(OrderIdIntervalVo input, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();
        logger.debug(Log4jLogger.SYSTEM_LOG,
                "OrderIdServiceClientController-refreshOrderIdInterval-input====" + JsonUtil.toString(input));
        iOrderIdServiceImplClient.refreshOrderIdInterval(input);
        logger.debug(Log4jLogger.SYSTEM_LOG,
                "OrderIdServiceClientController-refreshOrderIdInterval-刷新订单号区间结束====" + JsonUtil.toString(input));
        RestUtil.write(response, responseVo);
    }
}
