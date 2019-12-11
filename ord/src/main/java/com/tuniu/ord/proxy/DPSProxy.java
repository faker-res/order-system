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
import com.tuniu.ord.vo.QueryRelationsIdVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 【部门类目管理系统代理类】
 * 
 * @author zhairongping
 *
 */
@Service
public class DPSProxy {

    /**
     * 根据产品线id、部门id查询关联关系
     * 
     * @param orderSynInfo
     * @return
     */
    public ResponseVo queryRelationsId(QueryRelationsIdVo input) {
        ResponseVo responseVo = new ResponseVo();
        String data = JsonUtil.toString(input);
        String result = TspUtil.getTspResp(SystemConstants.QUERY_RELATIONS_ID, data, SystemConstants.HTTP_GET);
        if (null == result || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("根据产品线id、部门id查询关联关系接口异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

}
