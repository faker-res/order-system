/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年7月28日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.proxy;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.util.TspUtil;
import com.tuniu.ord.vo.RTXInputVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * RTX代理类
 * 
 * @author zhairongping
 * 
 */
public class RTXProxy {

    /**
     * 发RTX提醒
     * 
     * @param input
     * @return
     */
    public static ResponseVo sendRTX(RTXInputVo input) {
        ResponseVo responseVo = new ResponseVo();
        if (validateRTXInputVo(responseVo, input) == false) {
            return responseVo;
        }
        String clientData = JsonUtil.toString(input);
        String result = null;
        if (input.getStepId() == 1) {
            result = RestUtil.execute("http://10.40.50.72:11314/rtx/rtxnews/send", SystemConstants.HTTP_POST, clientData);
        } else {
            result = TspUtil.getTspResp(SystemConstants.SEND_RTX_URL, clientData, SystemConstants.HTTP_POST);
        }
        if (result == null || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("RTX系统异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

    /**
     * 检验RTX消息实体
     * 
     * @param responseVo
     * @param input
     * @return
     */
    private static boolean validateRTXInputVo(ResponseVo responseVo, RTXInputVo input) {
        boolean flag = false;
        if (input == null) {
            responseVo.setSuccess(false);
            responseVo.setMsg("RTX消息实体不能为空!");
            return flag;
        }
        if (null == input.getTitle() || "".equals(input.getTitle())) {
            responseVo.setSuccess(false);
            responseVo.setMsg("标题不能为空!");
            return flag;
        }
        if (null == input.getContent() || "".equals(input.getContent())) {
            responseVo.setSuccess(false);
            responseVo.setMsg("内容不能为空!");
            return flag;
        }
        if (null == input.getUids() || input.getUids().size() == 0) {
            responseVo.setSuccess(false);
            responseVo.setMsg("接受人不能为空!");
            return flag;
        }
        flag = true;
        return flag;
    }

}
