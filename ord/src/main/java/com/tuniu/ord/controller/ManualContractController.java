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
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.ResponseUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.domain.ManualContract;
import com.tuniu.ord.service.ManualContractService;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author zhoujie8
 * 
 */
@Controller
@RequestMapping("/manual/contract")
public class ManualContractController {
    private static final Logger logger = LoggerFactory.getLogger(ManualContractController.class);

    @Resource
    private ManualContractService manualContractService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(ManualContract manualContract, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            manualContractService.addManualContract(manualContract);
        } catch (OrdCustomException e) {
            logger.error("添加图片失败！", e);
            vo.setSuccess(false);
            vo.setErrorCode(e.getErrCode());
            vo.setMsg(e.getMessage());
        }

        RestUtil.write(response, vo);
    }

    @RequestMapping("/list")
    public void queryList(Integer manualOrderId, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        List<ManualContract> list = new ArrayList<ManualContract>();
        try {
            list = manualContractService.queryContractList(manualOrderId);
        } catch (OrdCustomException e) {
            logger.error("查询合同失败！", e);
            vo.setSuccess(false);
            vo.setErrorCode(e.getErrCode());
            vo.setMsg(e.getMessage());
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("count", list.size());
        vo.setData(map);
        RestUtil.write(response, vo);
    }

    @RequestMapping("/del")
    public void delContract(Integer id, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            manualContractService.delContractById(id);
        } catch (OrdCustomException e) {
            logger.error("查询合同失败！", e);
            vo.setSuccess(false);
            vo.setErrorCode(e.getErrCode());
            vo.setMsg(e.getMessage());
        }

        RestUtil.write(response, vo);
    }
}
