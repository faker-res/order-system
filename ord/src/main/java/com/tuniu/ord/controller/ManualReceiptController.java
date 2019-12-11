/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月7日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.ResponseUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.domain.ManualReceipt;
import com.tuniu.ord.service.ManualReceiptService;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author zhoujie8
 * 
 */
@Controller
@RequestMapping("/manual/receipt")
public class ManualReceiptController {
    private static final Logger logger = LoggerFactory.getLogger(ManualReceiptController.class);

    @Resource
    private ManualReceiptService manualReceiptService;

    @RequestMapping("/add")
    public void addManualReceipt(ManualReceipt manualReceipt, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            manualReceiptService.addManualReceipt(manualReceipt);
        } catch (OrdCustomException e) {
            logger.error("添加收款信息失败！", e);
            vo.setSuccess(false);
            vo.setErrorCode(e.getErrCode());
            vo.setMsg(e.getMessage());
        }

        RestUtil.write(response, vo);
    }

    @RequestMapping("/update")
    public void updateManualReceiptById(ManualReceipt manualReceipt, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            manualReceiptService.updateManualReceipt(manualReceipt);
        } catch (OrdCustomException e) {
            logger.error("修改收款信息失败！", e);
            vo.setSuccess(false);
            vo.setErrorCode(e.getErrCode());
            vo.setMsg(e.getMessage());
        }

        RestUtil.write(response, vo);
    }

    @RequestMapping("/del")
    public void delManualReceiptById(Integer id, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            manualReceiptService.delManualReceipt(id);
        } catch (OrdCustomException e) {
            logger.error("删除收款信息失败！", e);
            vo.setSuccess(false);
            vo.setErrorCode(e.getErrCode());
            vo.setMsg(e.getMessage());
        }

        RestUtil.write(response, vo);
    }

    @RequestMapping("/list")
    public void queryManualReceiptList(Integer manualOrderId, HttpServletResponse response) {
        List<ManualReceipt> manualReceipt = new ArrayList<ManualReceipt>();
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            manualReceipt = manualReceiptService.queryManualReceipt(manualOrderId);
        } catch (Exception e) {
            vo.setSuccess(false);
            vo.setMsg(e.getMessage());
            logger.error("查询订单增合同信息失败！", e);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", manualReceipt);
        map.put("count", manualReceipt.size());
        vo.setData(map);
        RestUtil.write(response, vo);
    }
}
