/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年7月14日                                                      
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

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.util.UserSessionUtil;
import com.tuniu.ord.service.ChannelOrderService;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.ResultVo;
import com.tuniu.ord.vo.channelorder.ChannelOrderQueryInputVo;
import com.tuniu.ord.vo.channelorder.ChannelOrderQueryOutputVo;
import com.tuniu.ord.vo.channelorder.DealCommonVo;

/**
 * @author zhairongping
 *
 */
@Controller
@RequestMapping("/rest/channel-order")
public class ChannelOrderController {
    private static final Logger LOG = LoggerFactory.getLogger(ChannelOrderController.class);

    @Resource
    private ChannelOrderService channelOrderServiceImpl;

    /**
     * 
     * 外部订单查询接口
     * 
     * @param request
     * @param response
     * @param channelOrderQueryVo
     */
    @RequestMapping(value = "/get-channel-order-list", method = RequestMethod.GET)
    public void getChannelOrderList(HttpServletRequest request, HttpServletResponse response,
            ChannelOrderQueryInputVo channelOrderQueryInputVo) {
        ResponseVo responseVo = new ResponseVo();

        ResultVo resultVo = channelOrderServiceImpl.validateChannelOrderQuery(channelOrderQueryInputVo);
        if (!resultVo.isSuccess()) {
            responseVo.setSuccess(false);
            responseVo.setMsg(resultVo.getMsg());
            RestUtil.write(response, responseVo);
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        Integer count = channelOrderServiceImpl.getChannelOrderNum(channelOrderQueryInputVo);
        map.put("count", count);
        if (count == null || count.intValue() == 0) {
            map.put("rows", null);
            responseVo.setData(map);
            RestUtil.write(response, responseVo);
            return;
        }

        List<ChannelOrderQueryOutputVo> rows = channelOrderServiceImpl.getChannelOrderList(channelOrderQueryInputVo);
        map.put("rows", rows);
        responseVo.setData(map);

        LOG.info("getChannelOrderList uid:[{}],nickname:[{}],param:[{}],result:[{}]", UserSessionUtil.getUid(),
                UserSessionUtil.getNickname(), JsonUtil.toString(channelOrderQueryInputVo), JsonUtil.toString(map));
        RestUtil.write(response, responseVo);
    }

    /**
     * 发起确认处理接口
     * 
     * @param request
     * @param response
     * @param dealCommonVo
     */
    @RequestMapping(value = "/deal-channel-order-confirm", method = RequestMethod.POST)
    public void dealChannelOrderConfirm(HttpServletRequest request, HttpServletResponse response, DealCommonVo dealCommonVo) {
        ResponseVo responseVo = new ResponseVo();

        ResultVo resultVo = channelOrderServiceImpl.validateChannelOrder(dealCommonVo);
        if (!resultVo.isSuccess()) {
            responseVo.setSuccess(false);
            responseVo.setMsg(resultVo.getMsg());
            RestUtil.write(response, responseVo);
            return;
        }

        responseVo = channelOrderServiceImpl.dealChannelOrderConfirm(dealCommonVo);
        RestUtil.write(response, responseVo);
    }

}
