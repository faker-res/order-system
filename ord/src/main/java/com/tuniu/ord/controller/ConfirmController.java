/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.Logging.Log4jLogger;
import com.tuniu.ord.core.Logging.LogFactory;
import com.tuniu.ord.service.ConfirmService;
import com.tuniu.ord.vo.CancelConfirmInputVo;
import com.tuniu.ord.vo.ConfirmFeedBackInputVo;
import com.tuniu.ord.vo.ConfirmInputVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 【提供给API调用的接口】
 * 
 * @author zhairongping
 *
 */
@Controller
@RequestMapping("/api/confirm")
public class ConfirmController {
    private static Log4jLogger logger = LogFactory.getLogger(ConfirmController.class);

    @Resource
    private ConfirmService confirmServiceImpl;

    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 发起确认
     * 
     * @param request
     * @param response
     * @param confirmInputVo
     */
    @Deprecated
    // @RequestMapping(value = "/initiateConfirm", method = RequestMethod.POST)
    public void initiateConfirm(HttpServletRequest request, HttpServletResponse response, ConfirmInputVo confirmInputVo) {
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>【发起确认请求参数】" + JsonUtil.toString(confirmInputVo));
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【initiateConfirm】step 1:发起确认主线程"
                + Thread.currentThread().getName() + "开始时间【" + df.format(new Date()) + "】");
        ResponseVo responseVo = confirmServiceImpl.initiateConfirm(confirmInputVo);
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【initiateConfirm】step 1:发起确认主线程"
                + Thread.currentThread().getName() + "结束时间【" + df.format(new Date()) + "】");
        RestUtil.write(response, responseVo);
    }

    /**
     * 取消确认
     * 
     * @param request
     * @param response
     * @param cancelConfirmInputVo
     */
    @Deprecated
    // @RequestMapping(value = "/cancelConfirm", method = RequestMethod.POST)
    public void cancelConfirm(HttpServletRequest request, HttpServletResponse response,
            CancelConfirmInputVo cancelConfirmInputVo) {
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>【取消确认请求参数】" + JsonUtil.toString(cancelConfirmInputVo));
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【cancelConfirm】step 1:取消确认流程开始");
        ResponseVo responseVo = confirmServiceImpl.cancelConfirm(cancelConfirmInputVo);
        logger.debug(Log4jLogger.SYSTEM_LOG, ">>>>>>>>>>>>>>>>>>>>>>>>>【cancelConfirm】step 1:取消确认流程结束");
        RestUtil.write(response, responseVo);
    }

    /**
     * 
     * 调用API确认反馈后门:获取反馈报文,更改sign,使用公司的Http接口工具调用这个后门接口
     * 
     * @param request
     * @param response
     * @param cancelConfirmInputVo
     */
    @Deprecated
    // @RequestMapping(value = "/confirmFeedBack", method = RequestMethod.POST)
    public void confirmFeedBack(HttpServletRequest request, HttpServletResponse response,
            ConfirmFeedBackInputVo confirmFeedBackInputVo) {
        ResponseVo responseVo = new ResponseVo();
        responseVo = confirmServiceImpl.lanuchConfirm(confirmFeedBackInputVo.getSign(), confirmFeedBackInputVo.getProducts(),
                true, confirmFeedBackInputVo.getSessionId());
        RestUtil.write(response, responseVo);
    }

}
