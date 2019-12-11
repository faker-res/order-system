/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年7月14日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.service.ChannelOrderExceptionService;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.channelorder.DealCommonVo;

@Controller
@RequestMapping("/rest/order")
public class OrderExceptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderExceptionController.class);

    @Resource
    private ChannelOrderExceptionService channelOrderExceptionService;

    @RequestMapping(value = "/change-tourist", method = RequestMethod.POST)
    public void updateRedundantTourist(DealCommonVo channelOrderUpdateVo, HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();

        try {
            channelOrderExceptionService.updateRedundantTourist(channelOrderUpdateVo);
        } catch (Exception e) {
            LOGGER.error("更新人员信息错误", e);
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        }
        RestUtil.write(response, responseVo);
    }
}
