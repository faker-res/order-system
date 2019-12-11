/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.ResponseUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.service.ManualFinishSignService;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 完成签约
 * 
 * @author zhoujie8
 * 
 */
@Controller
@RequestMapping("/finishSign")
public class ManualFinishSignController {
    @Resource
    private ManualFinishSignService manualFinishSign;

    @RequestMapping("/finish")
    public void finish(Integer manualOrderId, HttpServletResponse response) {
        ResponseVo vo = ResponseUtil.buildSuccess();
        try {
            vo.setData(manualFinishSign.finishSign(manualOrderId));
        } catch (OrdCustomException e) {
            vo = ResponseUtil.buildFailure(e);
            vo.setMsg(e.getMessage());
        }

        RestUtil.write(response, vo);
    }
}
