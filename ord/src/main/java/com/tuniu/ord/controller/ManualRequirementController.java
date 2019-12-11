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
import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.ResponseUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.service.CommonOrderService;
import com.tuniu.ord.service.ManualRequirementService;
import com.tuniu.ord.vo.BaseManualVo;
import com.tuniu.ord.vo.ManualRequirementVo;
import com.tuniu.ord.vo.RequirementVo;
import com.tuniu.ord.vo.ResponseVo;

@Controller
@RequestMapping("/rest/manual-order/requirement")
public class ManualRequirementController {
    
    private static Logger logger = LoggerFactory.getLogger(ManualRequirementController.class);
    
    @Resource
    private ManualRequirementService manualRequirementService;
    
    @Resource
    private CommonOrderService commonOrderService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveRequirement(ManualRequirementVo vo, HttpServletRequest request, HttpServletResponse response) {
        logger.info("保存需求:" + JsonUtil.toString(vo));
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getManualOrderId() == null || vo.getProductId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        try {
            manualRequirementService.saveRequirement(vo);
        } catch (OrdCustomException e) {
            logger.error("保存需求出错:" + e);
            responseVo = ResponseUtil.buildFailure(e);
        } catch (Exception e) {
            logger.error("保存需求出错:" + e);
            responseVo = ResponseUtil.buildFailure(e);
        }
        RestUtil.write(response, responseVo);
    }
    
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public void queryRequirement(BaseManualVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        responseVo.setData(commonOrderService.getRequirement(vo.getManualOrderId()));
        RestUtil.write(response, responseVo);
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteRequirement(RequirementVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getRequirementId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        manualRequirementService.removeRequirement(vo.getRequirementId());
        RestUtil.write(response, responseVo);
    }
}
