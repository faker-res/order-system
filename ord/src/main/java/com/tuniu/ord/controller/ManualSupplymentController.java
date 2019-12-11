/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.util.ArrayList;
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

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.ResponseUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.domain.ManualSupplyment;
import com.tuniu.ord.service.ManualSupplymentService;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 订单增补项接口
 * 
 * @author zhoujie8
 * 
 */
@Controller
@RequestMapping("/manual/supplyment")
public class ManualSupplymentController {

    private static final Logger logger = LoggerFactory.getLogger(ManualSupplymentController.class);

    @Resource
    private ManualSupplymentService manualSupplymentService;

    @RequestMapping("/list")
    public void querySupplyment(Integer manualOrderId, HttpServletResponse response) {
        List<ManualSupplyment> supplyment = new ArrayList<ManualSupplyment>();
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            supplyment = manualSupplymentService.querySupplyment(manualOrderId);
        } catch (Exception e) {
            vo.setSuccess(false);
            vo.setMsg(e.getMessage());
            logger.error("查询订单增补项失败！", e);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", supplyment);
        map.put("count", supplyment.size());
        vo.setData(map);
        System.out.println(JsonUtil.toString(vo));
        RestUtil.write(response, vo);
    }

    @RequestMapping("/update")
    public void updateSupplyment(ManualSupplyment supplyment, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            manualSupplymentService.updateSupplyment(supplyment);
        } catch (OrdCustomException e) {
            vo.setMsg(e.getMessage());
            vo.setErrorCode(e.getErrCode());
            vo.setSuccess(false);
        }

        RestUtil.write(response, vo);
    }

    @RequestMapping("/add")
    public void addSupplyment(ManualSupplyment supplyment, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            manualSupplymentService.addSupplyment(supplyment);
        } catch (OrdCustomException e) {
            vo.setSuccess(false);
            vo.setErrorCode(e.getErrCode());
            vo.setMsg(e.getMessage());
        }

        RestUtil.write(response, vo);
    }

    @RequestMapping("/del")
    public void delSupplyment(Integer id, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            manualSupplymentService.delSupplyment(id);
        } catch (OrdCustomException e) {
            vo.setSuccess(false);
            vo.setErrorCode(e.getErrCode());
            vo.setMsg(e.getMessage());
        }

        RestUtil.write(response, vo);
    }

    @RequestMapping("/priceDetail")
    public void manualPriceDetail(Integer manualOrderId, HttpServletResponse response) {
        String priceDetail = "";
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            priceDetail = manualSupplymentService.queryPriceDetail(manualOrderId);
        } catch (Exception e) {
            vo.setSuccess(false);
            vo.setMsg(e.getMessage());
            logger.error("查询订单价格详情出错！", e);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("priceDetail", priceDetail);
        vo.setData(map);
        RestUtil.write(response, vo);
    }
}
