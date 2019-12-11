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
import com.tuniu.ord.common.util.TspUtil;
import com.tuniu.ord.vo.OrderSynInfo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 【结算平台系统代理类】
 * 
 * @author zhairongping
 *
 */
@Service
public class SCSProxy {

    /**
     * 同步订单信息
     * 
     * @param orderSynInfo
     * @return
     */
    public ResponseVo synOrder(OrderSynInfo orderSynInfo) {
        ResponseVo responseVo = new ResponseVo();
        String data = JsonUtil.toString(orderSynInfo);
        String result = TspUtil.getTspResp(SystemConstants.SYN_ORDER, data, SystemConstants.HTTP_POST);
        if (null == result || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("同步订单信息接口异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

    /**
     * 更新订单信息
     * 
     * @param orderSynInfo
     * @return
     */
    public ResponseVo updateOrder(OrderSynInfo orderSynInfo) {
        ResponseVo responseVo = new ResponseVo();
        String data = JsonUtil.toString(orderSynInfo);
        String result = TspUtil.getTspResp(SystemConstants.UPDATE_ORDER, data, SystemConstants.HTTP_POST);
        if (null == result || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("更新订单信息接口异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

}
