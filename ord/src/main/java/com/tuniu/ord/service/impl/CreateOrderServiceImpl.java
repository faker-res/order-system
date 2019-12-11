/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月7日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.common.task.ManualTaskVo;
import com.tuniu.ord.common.task.TaskFlow;
import com.tuniu.ord.core.init.ApplicationContextProxy;
import com.tuniu.ord.enums.DMSServiceEnum;
import com.tuniu.ord.enums.OrderModeEnum;
import com.tuniu.ord.persistence.ManualOrderMapper;
import com.tuniu.ord.service.CreateOrderService;
import com.tuniu.ord.service.DMSCommonService;
import com.tuniu.ord.service.OrderService;
import com.tuniu.ord.vo.ManualOrderQueryInputVo;
import com.tuniu.ord.vo.ManualOrderQueryOutputVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.createOrder.DMSQueryBaseInputVo;
import com.tuniu.ord.vo.createOrder.ManualOrderQueryInter;
import com.tuniu.ord.vo.createOrder.ManualOrderQueryVo;
import com.tuniu.ord.vo.createOrder.OrderBaseParamInputVo;

/**
 * @author zhairongping
 *
 */
@Service
public class CreateOrderServiceImpl implements CreateOrderService {
    @Resource
    private ApplicationContextProxy applicationContextProxy;

    @Resource
    private ManualOrderMapper manualOrderMapper;

    @Resource
    private TaskFlow taskFlow;

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.CreateOrderService#validateOrder(com.tuniu.ord.vo.createOrder.OrderBaseParamInputVo,
     * com.tuniu.ord.enums.OrderModeEnum)
     */
    @Override
    public Map<String, Object> validateOrder(OrderBaseParamInputVo input, OrderModeEnum orderMode) {
        Object obj = applicationContextProxy.getBean(orderMode.getOrderMode());
        OrderService orderService = (OrderService) obj;
        return orderService.validateOrder(input);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.CreateOrderService#createOrder(com.tuniu.ord.vo.createOrder.OrderBaseParamInputVo,
     * java.lang.String)
     */
    @Override
    public Integer createOrder(OrderBaseParamInputVo input, OrderModeEnum orderMode) {
        Object obj = applicationContextProxy.getBean(orderMode.getOrderMode());
        OrderService orderService = (OrderService) obj;
        return orderService.createOrder(input);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.CreateOrderService#provideDMSService(com.tuniu.ord.vo.createOrder.DMSQueryBaseInputVo,
     * com.tuniu.ord.enums.DMSServiceEnum)
     */
    @Override
    public ResponseVo provideDMSService(DMSQueryBaseInputVo input, DMSServiceEnum DMSService) {
        ResponseVo responseVo = new ResponseVo();
        Object obj = applicationContextProxy.getBean(DMSService.getServiceName());
        DMSCommonService service = (DMSCommonService) obj;
        // 统一提供DMS服务
        Map<String, Object> map = service.provideDMSService(input);

        responseVo.setSuccess((boolean) map.get("success"));
        responseVo.setMsg((String) map.get("msg"));
        responseVo.setData(map.get("data"));
        return responseVo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.CreateOrderService#getManualOrderList(com.tuniu.ord.vo.createOrder.ManualOrderQueryInter)
     */
    @Override
    public List<ManualOrderQueryInter> getManualOrderList(ManualOrderQueryInter input) {
        List<ManualOrderQueryInter> orderList = new ArrayList<ManualOrderQueryInter>();
        orderList = manualOrderMapper.getManualOrderList(input);
        if (orderList != null && orderList.size() > 0) {
            for (ManualOrderQueryInter moqi : orderList) {
                ManualOrderQueryVo manualOrderQueryVo = (ManualOrderQueryVo) moqi;
                ManualTaskVo manualTaskVo = taskFlow.getCurrentTask(manualOrderQueryVo.getManualOrderId());
                if (manualTaskVo != null) {
                    manualOrderQueryVo.setManualOrderURL(manualTaskVo.getUrl());
                }
            }
        }
        return orderList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.CreateOrderService#countManualOrderList(com.tuniu.ord.vo.createOrder.ManualOrderQueryInter)
     */
    @Override
    public Integer countManualOrderList(ManualOrderQueryInter input) {
        Integer count = new Integer(0);
        count = manualOrderMapper.countManualOrderList(input);
        return count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.CreateOrderService#countManualOrderList(com.tuniu.ord.vo.ManualOrderQueryInputVo)
     */
    @Override
    public Integer countNewManualOrder(ManualOrderQueryInputVo input) {
        Integer count = new Integer(0);
        count = manualOrderMapper.countNewManualOrder(input);
        return count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.CreateOrderService#getManualOrderList(com.tuniu.ord.vo.ManualOrderQueryInputVo)
     */
    @Override
    public List<ManualOrderQueryOutputVo> getNewManualOrder(ManualOrderQueryInputVo input) {
        List<ManualOrderQueryOutputVo> orders = manualOrderMapper.getNewManualOrder(input);
        if (orders != null && orders.size() > 0) {
            for (ManualOrderQueryOutputVo order : orders) {
                ManualTaskVo manualTaskVo = taskFlow.getCurrentTask(order.getManualOrderId());
                if (manualTaskVo != null) {
                    order.setManualOrderURL(manualTaskVo.getUrl());
                }
                int tourNum = 0;
                if (order.getAdultCount() != null) {
                    tourNum += order.getAdultCount().intValue();
                }
                if (order.getChildCount() != null) {
                    tourNum += order.getChildCount().intValue();
                }
                order.setTourNum(tourNum);
                // 调用产品系统
                /**
                 * http://public-api.prd.dota.tuniu.org/prd/rest/product/list/query
                 */
                /**
                 * { "tenantId": "tuniu", "id": "2000000297", "start": 0, "limit": 1 }
                 */
                // 调用组团系统
            }
        }
        return orders;
    }

}
