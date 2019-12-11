/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月20日                                                      
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

import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.service.CommonOrderService;
import com.tuniu.ord.service.ManualOrderConfirmService;
import com.tuniu.ord.vo.ResponseVo;

@Controller
@RequestMapping("/api/manual/confirm")
public class ManualConfirmController {
    private static Logger logger = LoggerFactory.getLogger(ManualConfirmController.class);

    @Resource
    private ManualOrderConfirmService confirmService;
    
    @Resource
    private CommonOrderService commonOrderService;

    /**
     * 发起确认
     * 
     * @param request
     * @param response
     * @param confirmInputVo
     */

    @RequestMapping(value = "/initiateConfirm", method = RequestMethod.POST)
    public void initiateConfirm(Integer orderOccupyId, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();
        try {
            responseVo = confirmService.confirm(orderOccupyId);
        } catch (Exception e) {
            logger.error("人工下单出错", e);
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        }
        RestUtil.write(response, responseVo);
    }

    /**
     * 订单完成确认
     * 
     * @param manualOrderId
     * @param request
     * @param response
     */
    @RequestMapping(value = "/finishConfirm", method = RequestMethod.POST)
    public void finishConfirm(Integer manualOrderId, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();
        try {
            confirmService.finishConfirm(manualOrderId);
            commonOrderService.addRemarkBySystem(manualOrderId, "订单完成确认");
        } catch (Exception e) {
            logger.error("完成确认订单出错", e);
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        }
        RestUtil.write(response, responseVo);
    }
}
