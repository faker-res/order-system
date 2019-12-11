package com.tuniu.ord.common.task;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.ord.common.util.ResponseUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.vo.BaseManualVo;
import com.tuniu.ord.vo.ResponseVo;

@Controller
@RequestMapping("/rest/manual-order/taskflow")
public class TaskFlowController {

    @Resource
    private TaskFlow taskFlow;
    
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public void initTaskFlow(BaseManualVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        
        try {
            taskFlow.initTaskFlow(vo.getManualOrderId());
        } catch (Exception e) {
            responseVo = ResponseUtil.buildFailure(e);
        }
        RestUtil.write(response, responseVo);
    }
    
    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public void finishTaskFlow(TaskFlowVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getManualOrderId() == null || vo.getTaskCode() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        
        try {
            taskFlow.finish(vo.getManualOrderId(), OrderTaskEnum.getTaskByKey(vo.getTaskCode()));
        } catch (Exception e) {
            responseVo = ResponseUtil.buildFailure(e);
        }
        RestUtil.write(response, responseVo);
    }
    
    @RequestMapping(value = "/get-current-task", method = RequestMethod.GET)
    public void getCurrentTask(TaskFlowVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        
        try {
            responseVo.setData(taskFlow.getCurrentTask(vo.getManualOrderId()));
        } catch (Exception e) {
            responseVo = ResponseUtil.buildFailure(e);
        }
        RestUtil.write(response, responseVo);
    }
    
    @RequestMapping(value = "/get-all-task", method = RequestMethod.GET)
    public void getAllTask(TaskFlowVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        
        try {
            responseVo.setData(taskFlow.getAllTask(vo.getManualOrderId()));
        } catch (Exception e) {
            responseVo = ResponseUtil.buildFailure(e);
        }
        RestUtil.write(response, responseVo);
    }
}
