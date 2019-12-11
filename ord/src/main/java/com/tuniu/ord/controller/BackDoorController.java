/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年7月14日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.util.UserSessionUtil;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.proxy.SCSProxy;
import com.tuniu.ord.service.BackDoorService;
import com.tuniu.ord.vo.OrderSynInfo;
import com.tuniu.ord.vo.ProductLineIdOuterVo;
import com.tuniu.ord.vo.ProductLineIdVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author zhairongping
 *
 */
@Controller
@RequestMapping("/rest/back-door")
public class BackDoorController {
    private static final Logger LOG = LoggerFactory.getLogger(BackDoorController.class);

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    @Resource
    private OrdDealOrderMapper ordDealOrderMapper;

    @Resource
    private SCSProxy sCSProxy;

    @Resource
    private BackDoorService backDoorServiceImpl;

    /**
     * 更改销售单基本信息
     * 
     * @param request
     * @param response
     * @param ordSalesOrder
     */
    @RequestMapping(value = "/upd-sales-order", method = RequestMethod.POST)
    public void updSalesOrder(HttpServletRequest request, HttpServletResponse response, OrdSalesOrder ordSalesOrder) {
        ResponseVo responseVo = new ResponseVo();
        if (null == ordSalesOrder.getId()) {
            responseVo.setSuccess(false);
            responseVo.setMsg("销售单ID必填");
            RestUtil.write(response, responseVo);
            return;
        }
        LOG.info("【更改销售单基本信息】 userId:[{}],nickname:[{}],param:[{}]", UserSessionUtil.getUid(), UserSessionUtil.getNickname(),
                JsonUtil.toString(ordSalesOrder));
        ordSalesOrderMapper.updateByPrimaryKeySelective(ordSalesOrder);
        RestUtil.write(response, responseVo);
    }

    /**
     * 更改切位单基本信息
     * 
     * @param request
     * @param response
     * @param ordDealOrder
     */
    @RequestMapping(value = "/upd-deal-order", method = RequestMethod.POST)
    public void updDealOrder(HttpServletRequest request, HttpServletResponse response, OrdDealOrder ordDealOrder) {
        ResponseVo responseVo = new ResponseVo();
        if (null == ordDealOrder.getId()) {
            responseVo.setSuccess(false);
            responseVo.setMsg("切位单ID必填");
            RestUtil.write(response, responseVo);
            return;
        }
        LOG.info("【更改切位单基本信息】 userId:[{}],nickname:[{}],param:[{}]", UserSessionUtil.getUid(), UserSessionUtil.getNickname(),
                JsonUtil.toString(ordDealOrder));
        ordDealOrderMapper.updateByPrimaryKeySelective(ordDealOrder);
        RestUtil.write(response, responseVo);
    }

    /**
     * 同步订单信息
     * 
     * @param request
     * @param response
     * @param orderSynInfo
     */
    @RequestMapping(value = "/syn-order", method = RequestMethod.POST)
    public void synOrder(HttpServletRequest request, HttpServletResponse response, OrderSynInfo orderSynInfo) {
        ResponseVo responseVo = new ResponseVo();
        LOG.info("【同步订单信息】 userId:[{}],nickname:[{}],param:[{}]", UserSessionUtil.getUid(), UserSessionUtil.getNickname(),
                JsonUtil.toString(orderSynInfo));
        responseVo = sCSProxy.synOrder(orderSynInfo);
        RestUtil.write(response, responseVo);
    }

    /**
     * 更新订单信息
     * 
     * @param request
     * @param response
     * @param orderSynInfo
     */
    @RequestMapping(value = "/update-order", method = RequestMethod.POST)
    public void updateOrder(HttpServletRequest request, HttpServletResponse response, OrderSynInfo orderSynInfo) {
        ResponseVo responseVo = new ResponseVo();
        LOG.info("【更新订单信息】 userId:[{}],nickname:[{}],param:[{}]", UserSessionUtil.getUid(), UserSessionUtil.getNickname(),
                JsonUtil.toString(orderSynInfo));
        responseVo = sCSProxy.updateOrder(orderSynInfo);
        RestUtil.write(response, responseVo);
    }

    /**
     * 查询产品线未关联部门的产品线
     * 
     * @param request
     * @param response
     * @param productLineIdOuterVo
     */
    @RequestMapping(value = "/get-not-related-product-line-ids", method = RequestMethod.GET)
    public void getNotRelatedProductLineIds(HttpServletRequest request, HttpServletResponse response,
            ProductLineIdOuterVo productLineIdOuterVo) {
        ResponseVo responseVo = new ResponseVo();

        if (!CollectionUtils.isNotEmpty(productLineIdOuterVo.getProductLineIds())) {
            responseVo.setSuccess(false);
            responseVo.setMsg("请求参数产品线为空");
            RestUtil.write(response, responseVo);
            return;
        }

        List<String> originalProductLineIds = new ArrayList<String>();
        for (ProductLineIdVo productLineIdVo : productLineIdOuterVo.getProductLineIds()) {
            originalProductLineIds.add(productLineIdVo.getProduct_line_id());
        }

        List<String> relatedProductLineIds = new ArrayList<String>();
        for (ProductLineIdVo productLineIdVo : productLineIdOuterVo.getProductLineIds()) {
            if (backDoorServiceImpl.isRelated(Integer.parseInt(productLineIdVo.getProduct_line_id()))) {
                relatedProductLineIds.add(productLineIdVo.getProduct_line_id());
            }
        }

        originalProductLineIds.removeAll(relatedProductLineIds);

        responseVo.setData(JsonUtil.toString(originalProductLineIds));
        RestUtil.write(response, responseVo);
    }

}
