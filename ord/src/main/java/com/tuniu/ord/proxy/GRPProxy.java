/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月15日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.proxy;

import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.vo.LossApplyInfoVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.SalesConfirmVo;
import com.tuniu.ord.vo.common.BaseVo;

/**
 * 【组团系统代理类】
 * 
 * @author zhairongping
 *
 */
@Service
public class GRPProxy {

    /**
     * 销售单下单
     * 
     * @param salesConfirmVo
     * @return
     */
    public ResponseVo sendSalesConfirm(SalesConfirmVo salesConfirmVo) {
        BaseVo.initTenantId(salesConfirmVo);
        ResponseVo responseVo = new ResponseVo();
        String result = RestUtil.execute(SystemInitParameter.sendSalesConfirm, SystemConstants.HTTP_POST,
                JsonUtil.toString(salesConfirmVo), 30, 30);
        if (null == result || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("销售单下单接口异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

    /**
     * D类向GRP发起核损申请
     * 
     * @param receiveInfoVo
     * @return
     */
    public ResponseVo applyLossInfo(LossApplyInfoVo lossApplyInfoVo) {
        BaseVo.initTenantId(lossApplyInfoVo);
        ResponseVo responseVo = new ResponseVo();
        String result = RestUtil.execute(SystemInitParameter.applyLossInfo, SystemConstants.HTTP_POST,
                JsonUtil.toString(lossApplyInfoVo));
        if (null == result || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("D类向GRP发起核损申请接口异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

}
