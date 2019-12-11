/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016-5-23                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.service.IOrderService;
import com.tuniu.ord.vo.CancelOrderVo;
import com.tuniu.ord.vo.QueryRealTimeAskVo;
import com.tuniu.ord.vo.RealTimeAskResponse;
import com.tuniu.ord.vo.RealTimeAskStockVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 现询 给 fad调用
 * 
 * @author douyanhui
 * 
 */
@Controller
@RequestMapping("/rest/api")
public class RealTimeAskController {

    /**
     * 记录日志信息
     */
    private static Logger LOGGER = LoggerFactory.getLogger(RealTimeAskController.class);

    @Resource
    private IOrderService orderService;

    /**
     * 现询
     * 
     * @param realTimeAskStockVo
     * @param response
     */
    @RequestMapping(path = "/stock-real-ask", method = RequestMethod.POST)
    public void queryStock(RealTimeAskStockVo realTimeAskStockVo, HttpServletResponse response) {
        LOGGER.info("realTimeAskStockVo:{}", JsonUtil.toString(realTimeAskStockVo));

        ResponseVo responseVo = new ResponseVo();
        try {
            if (realTimeAskStockVo.getType() == 0) { // 现询占位
                RealTimeAskResponse realTimeAskResponse = orderService.addStockRealTimeAsk(realTimeAskStockVo);
                responseVo.setData(realTimeAskResponse);
            } else if (realTimeAskStockVo.getType() == 2) { // 现询取消占位
                CancelOrderVo cancelOrderVo = new CancelOrderVo();
                cancelOrderVo.setOrderIds(new Integer[] { realTimeAskStockVo.getExtPurchaseId() });
                cancelOrderVo.setCancelCount(realTimeAskStockVo.getAdultNum() + realTimeAskStockVo.getChildNum());
                orderService.cancelOrder(cancelOrderVo);
                responseVo.setData(JsonUtil.toString(realTimeAskStockVo));
            } else {
                throw new SaaSSystemException("此现询类型不支持");
            }
        } catch (Exception e) {
            LOGGER.error("现询失败", e);
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        }
        RestUtil.write(response, responseVo);
    }

    @RequestMapping(path = "/number/stock-real-ask", method = RequestMethod.GET)
    public void queryRealTimeAskVo(QueryRealTimeAskVo queryRealTimeAskVo, HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();
        try {
            List<OrdDealOrder> dealOrders = orderService.queryRealAskNumber(queryRealTimeAskVo);
            responseVo.setData(dealOrders);
        } catch (Exception e) {
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        }
        RestUtil.write(response, responseVo);
    }

}
