/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月22日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.proxy;

import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.vo.ConfirmInputVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.common.BaseVo;

/**
 * 【订单系统】
 * 
 * @author zhairongping
 *
 */
@Service
public class ORDProxy {

    /**
     * 发起确认
     * 
     * @param confirmInputVo
     * @return
     */
    public ResponseVo newInitiateConfirm(ConfirmInputVo confirmInputVo) {
        BaseVo.initTenantId(confirmInputVo);
        ResponseVo responseVo = new ResponseVo();
        String data = JsonUtil.toString(confirmInputVo);
        String result = RestUtil.execute(SystemInitParameter.newInitiateConfirmAddress, SystemConstants.HTTP_POST, data);
        if (null == result || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("发起确认接口异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

}
