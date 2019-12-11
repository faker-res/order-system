package com.tuniu.ord.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.task.OrderTaskEnum;
import com.tuniu.ord.common.task.TaskFlow;
import com.tuniu.ord.common.util.ResponseUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.service.CommonOrderService;
import com.tuniu.ord.service.ManualReqireConfirmService;
import com.tuniu.ord.vo.RequirementVo;
import com.tuniu.ord.vo.ResponseVo;

@Controller
@RequestMapping("/rest/manual-order")
public class ManualRequireConfirmController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManualRequireConfirmController.class);
    
    @Resource
    private ManualReqireConfirmService reqireConfirmService;
    
    @Resource
    private CommonOrderService commonOrderService;
    
    @Resource
    private TaskFlow taskFlow;
    
    @RequestMapping(value = "/require-confirm", method = RequestMethod.POST)
    public void requireConfirm(RequirementVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getRequirementId() == null || vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        try {
            StringBuffer msg = new StringBuffer("");
            if (!reqireConfirmService.requireConfirm(vo.getManualOrderId(), vo.getRequirementId(), msg)) {
                responseVo.setSuccess(false);
                responseVo.setMsg(msg.toString());
            } else {
                taskFlow.finish(vo.getManualOrderId(), OrderTaskEnum.REQ_CONFIRM);
                responseVo.setData(taskFlow.getCurrentTask(vo.getManualOrderId()));
                commonOrderService.addRemarkBySystem(vo.getManualOrderId(), "订单完成需求确认");
            }
        } catch (OrdCustomException e) {
            LOGGER.error("需求确认出错", e);
            responseVo = ResponseUtil.buildFailure(e);
        } catch (Exception e) {
            LOGGER.error("需求确认出错", e);
            responseVo = ResponseUtil.buildFailure(OrdError.REQUIRE_CONFIRM_ERROR);
        }
        RestUtil.write(response, responseVo);
    }
}
