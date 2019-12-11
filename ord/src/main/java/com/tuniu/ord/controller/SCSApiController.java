/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年7月14日                                                      
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
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.util.UserSessionUtil;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.proxy.SCSProxy;
import com.tuniu.ord.service.SCSApiService;
import com.tuniu.ord.vo.OrderDetailQueryVo;
import com.tuniu.ord.vo.OrderSynInfo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.ResultVo;

/**
 * @author zhairongping
 *
 */
@Controller
@RequestMapping("/api/scs")
public class SCSApiController {
    private static final Logger LOG = LoggerFactory.getLogger(SCSApiController.class);

    @Resource
    private SCSProxy sCSProxy;

    @Resource
    private SCSApiService sCSApiServiceImpl;

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    /**
     * 同步订单信息
     * 
     * @param request
     * @param response
     * @param orderDetailQueryVo
     */
    @RequestMapping(value = "/scs-syn-order", method = RequestMethod.POST)
    public void scsSynOrder(HttpServletRequest request, HttpServletResponse response, OrderDetailQueryVo orderDetailQueryVo) {
        ResponseVo responseVo = new ResponseVo();
        Integer orderId = orderDetailQueryVo.getOrderId();

        ResultVo resultVo = checkOrderId(orderId);
        if (!resultVo.isSuccess()) {
            responseVo.setMsg(resultVo.getMsg());
            responseVo.setSuccess(false);
            RestUtil.write(response, responseVo);
            return;
        }

        OrderSynInfo orderSynInfo = new OrderSynInfo();
        sCSApiServiceImpl.initOrderSynInfo(orderId, orderSynInfo);
        ResponseVo sCsRes = sCSProxy.synOrder(orderSynInfo);

        LOG.info("【同步订单信息】  uid:[{}],nickname:[{}],orderId:[{}],success:[{}],msg:[{}],param:[{}]", UserSessionUtil.getUid(),
                UserSessionUtil.getNickname(), orderId, sCsRes.isSuccess(), sCsRes.getMsg(), JsonUtil.toString(orderSynInfo));
        responseVo.setMsg(sCsRes.getMsg());
        responseVo.setSuccess(sCsRes.isSuccess());
        RestUtil.write(response, responseVo);
    }

    /**
     * 更新订单信息
     * 
     * @param request
     * @param response
     * @param orderDetailQueryVo
     */
    @RequestMapping(value = "/scs-upd-order", method = RequestMethod.POST)
    public void scsUpdOrder(HttpServletRequest request, HttpServletResponse response, OrderDetailQueryVo orderDetailQueryVo) {
        ResponseVo responseVo = new ResponseVo();
        Integer orderId = orderDetailQueryVo.getOrderId();

        ResultVo resultVo = checkOrderId(orderId);
        if (!resultVo.isSuccess()) {
            responseVo.setMsg(resultVo.getMsg());
            responseVo.setSuccess(false);
            RestUtil.write(response, responseVo);
            return;
        }

        OrderSynInfo orderSynInfo = new OrderSynInfo();
        sCSApiServiceImpl.initOrderSynInfo(orderId, orderSynInfo);
        ResponseVo sCsRes = sCSProxy.updateOrder(orderSynInfo);

        LOG.info("【更新订单信息】  uid:[{}],nickname:[{}],orderId:[{}],success:[{}],msg:[{}],param:[{}]", UserSessionUtil.getUid(),
                UserSessionUtil.getNickname(), orderId, sCsRes.isSuccess(), sCsRes.getMsg(), JsonUtil.toString(orderSynInfo));
        responseVo.setMsg(sCsRes.getMsg());
        responseVo.setSuccess(sCsRes.isSuccess());
        RestUtil.write(response, responseVo);
    }

    /**
     * 添加校验规则
     * 
     * @param orderId
     * @return
     */
    private ResultVo checkOrderId(Integer orderId) {
        ResultVo resultVo = new ResultVo();
        if (null == orderId || orderId.intValue() == 0) {
            resultVo.setMsg("销售单号不能为空");
            resultVo.setSuccess(false);
            return resultVo;
        }
        OrdSalesOrder ordSalesOrder = ordSalesOrderMapper.selectByPrimaryKey(orderId);
        if (null == ordSalesOrder) {
            resultVo.setMsg("销售单不存在");
            resultVo.setSuccess(false);
            return resultVo;
        }
        return resultVo;
    }

}
