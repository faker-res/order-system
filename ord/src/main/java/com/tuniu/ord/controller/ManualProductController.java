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
import com.tuniu.ord.service.ManualProductQueryService;
import com.tuniu.ord.vo.ManualProductQueryVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.common.ListVo;

@Controller
@RequestMapping("/rest/manual-product")
public class ManualProductController {
    private static Logger LOGGER = LoggerFactory.getLogger(ManualProductController.class);

    @Resource
    private ManualProductQueryService manualProductQueryService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public void queryManualProduct(ManualProductQueryVo manualProductQueryVo, HttpServletRequest request,
            HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();
        try {
            ListVo listVo = manualProductQueryService.queryManualProduct(manualProductQueryVo);
            responseVo.setData(listVo);
        } catch (Exception e) {
            LOGGER.error("查询人工下单可用的产品出错", e);
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        }
        RestUtil.write(response, responseVo);
    }

    @RequestMapping(value = "/query-prd-select", method = RequestMethod.GET)
    public void queryPrdSelect(Integer manualOrderId, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = new ResponseVo();
        try {
            ListVo listVo = manualProductQueryService.queryPrdSelect(manualOrderId);
            responseVo.setData(listVo);
        } catch (Exception e) {
            LOGGER.error("查询已选产品详情", e);
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        }
        RestUtil.write(response, responseVo);
    }

}
