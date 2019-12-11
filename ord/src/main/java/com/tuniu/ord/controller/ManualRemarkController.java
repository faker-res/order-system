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
import com.tuniu.ord.common.util.ResponseUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.domain.ManualRemark;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.service.ManualRemarkService;
import com.tuniu.ord.vo.AddManualRemarkVo;
import com.tuniu.ord.vo.BaseManualVo;
import com.tuniu.ord.vo.ResponseVo;

@Controller
@RequestMapping("/rest/manual-order/remark")
public class ManualRemarkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManualRemarkController.class);
    
    @Resource
    private ManualRemarkService manualRemarkService;
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addRemark(AddManualRemarkVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        
        try {
            ManualRemark remark = new ManualRemark();
            remark.setManualOrderId(vo.getManualOrderId());
            remark.setType(vo.getType());
            remark.setContent(vo.getContent());
            manualRemarkService.saveRemark(remark);
        } catch (OrdCustomException e) {
            LOGGER.error("添加备注出错", e);
            responseVo = ResponseUtil.buildFailure(e);
        } catch (Exception e) {
            LOGGER.error("添加备注出错", e);
            responseVo = ResponseUtil.buildFailure(e);
        }
        RestUtil.write(response, responseVo);
    }
    
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public void queryRemark(BaseManualVo vo, HttpServletRequest request, HttpServletResponse response) {
        ResponseVo responseVo = ResponseUtil.buildSuccess();
        if(vo.getManualOrderId() == null) {
            responseVo = ResponseUtil.buildFailure(OrdError.PARAM_NULL_ERROR);
            RestUtil.write(response, responseVo);
            return;
        }
        responseVo.setData(manualRemarkService.queryRemarks(vo.getManualOrderId()));
        RestUtil.write(response, responseVo);
    }
}
