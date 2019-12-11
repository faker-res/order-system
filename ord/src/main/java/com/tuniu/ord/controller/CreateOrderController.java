/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.enums.DMSServiceEnum;
import com.tuniu.ord.enums.OrderModeEnum;
import com.tuniu.ord.service.CreateOrderService;
import com.tuniu.ord.vo.ManualOrderQueryInputVo;
import com.tuniu.ord.vo.ManualOrderQueryOutputVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.createOrder.ManualCreateOrderParamInputVo;
import com.tuniu.ord.vo.createOrder.ManualOrderQueryInter;
import com.tuniu.ord.vo.createOrder.ManualOrderQueryVo;
import com.tuniu.ord.vo.createOrder.MemberQueryInputVo;

/**
 * 下单服务
 * 
 * @author zhairongping
 *
 */
@Controller
@RequestMapping("/rest/manual")
public class CreateOrderController {
    private static final Logger LOG = LoggerFactory.getLogger(CreateOrderController.class);

    @Resource
    private CreateOrderService createOrderServiceImpl;

    /**
     * 人工下单服务接口
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/create-manual-order", method = RequestMethod.POST)
    public void createManualOrder(HttpServletRequest request, HttpServletResponse response,
            ManualCreateOrderParamInputVo input) {
        ResponseVo responseVo = new ResponseVo();
        LOG.info("人工下单服务接口入参:{}", JsonUtil.toString(input));

        Map<String, Object> map = createOrderServiceImpl.validateOrder(input, OrderModeEnum.MANUAL_CREATE_ORDER);
        boolean success = (boolean) map.get("success");
        if (!success) {
            responseVo.setSuccess(false);
            responseVo.setMsg((String) map.get("msg"));
            RestUtil.write(response, responseVo);
            return;
        }

        responseVo.setData(createOrderServiceImpl.createOrder(input, OrderModeEnum.MANUAL_CREATE_ORDER));
        RestUtil.write(response, responseVo);
    }

    /**
     * 根据公司名称模糊搜索接口
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/query-member-by-fullName", method = RequestMethod.GET)
    public void queryMemberByFullName(HttpServletRequest request, HttpServletResponse response,
            MemberQueryInputVo memberQueryInputVo) {
        ResponseVo responseVo = createOrderServiceImpl.provideDMSService(memberQueryInputVo,
                DMSServiceEnum.QueryByFullNameService);
        RestUtil.write(response, responseVo);
    }

    /**
     * 查询对接人信息
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/query-member-contacts", method = RequestMethod.GET)
    public void queryMemberContacts(HttpServletRequest request, HttpServletResponse response,
            MemberQueryInputVo memberQueryInputVo) {
        ResponseVo responseVo = createOrderServiceImpl.provideDMSService(memberQueryInputVo,
                DMSServiceEnum.QueryContactPersonService);
        RestUtil.write(response, responseVo);
    }

    /**
     * 人工订单查询功能(简化版)
     * 
     * @param request
     * @param response
     * @param input
     */
    @RequestMapping(value = "/get-manual-order-list", method = RequestMethod.GET)
    public void getManualOrderList(HttpServletRequest request, HttpServletResponse response, ManualOrderQueryVo input) {
        ResponseVo responseVo = new ResponseVo();
        Map<String, Object> map = new HashMap<String, Object>();

        Integer count = createOrderServiceImpl.countManualOrderList(input);
        List<ManualOrderQueryInter> rows = createOrderServiceImpl.getManualOrderList(input);
        map.put("count", count);
        map.put("rows", rows);
        responseVo.setData(map);

        RestUtil.write(response, responseVo);
    }

    /**
     * 人工订单查询功能(拓展版)
     * 
     * @param request
     * @param response
     * @param input
     */
    @RequestMapping(value = "/query-manual-order", method = RequestMethod.GET)
    public void queryManualOrder(HttpServletRequest request, HttpServletResponse response, ManualOrderQueryInputVo input) {
        ResponseVo responseVo = new ResponseVo();
        Map<String, Object> map = new HashMap<String, Object>();

        Integer count = createOrderServiceImpl.countNewManualOrder(input);
        List<ManualOrderQueryOutputVo> rows = createOrderServiceImpl.getNewManualOrder(input);
        map.put("count", count);
        map.put("rows", rows);
        responseVo.setData(map);

        RestUtil.write(response, responseVo);
    }

}
