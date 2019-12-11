/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月8日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.service.DMSCommonService;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.common.BaseVo;
import com.tuniu.ord.vo.createOrder.DMSQueryBaseInputVo;
import com.tuniu.ord.vo.createOrder.MemberQueryInputVo;
import com.tuniu.ord.vo.createOrder.MemberQueryOutputVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 根据公司名称模糊搜索
 * 
 * @author zhairongping
 *
 */
@Service(value = "queryByFullNameService")
public class QueryByFullNameDMSCommonServiceImpl implements DMSCommonService {

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.DMSCommonService#provideDMSService(com.tuniu.ord.vo.common.DMSInputBaseInter)
     */
    @Override
    public Map<String, Object> provideDMSService(DMSQueryBaseInputVo input) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!(input instanceof MemberQueryInputVo)) {
            map.put("success", false);
            map.put("msg", "入参类型不匹配");
            map.put("data", null);
            return map;
        }
        MemberQueryInputVo memberQueryInputVo = (MemberQueryInputVo) input;
        BaseVo.initTenantId(memberQueryInputVo);
        String result = RestUtil.execute(SystemInitParameter.queryMemberByFullName, SystemConstants.HTTP_GET,
                JsonUtil.toString(memberQueryInputVo));
        if (null == result || "".equals(result)) {
            map.put("success", false);
            map.put("msg", "调用根据公司名称模糊搜索接口异常");
            map.put("data", null);
            return map;
        } else {
            ResponseVo responseVo = new ResponseVo();
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
            if (!responseVo.isSuccess()) {
                map.put("success", false);
                map.put("msg", responseVo.getMsg());
                map.put("data", null);
                return map;
            }
            JSONArray array = JSONArray.fromObject(JsonUtil.toString(responseVo.getData()));
            int size = array.size();
            List<MemberQueryOutputVo> list = new ArrayList<MemberQueryOutputVo>();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    JSONObject jo = array.getJSONObject(i);
                    MemberQueryOutputVo memberQueryOutputVo = new MemberQueryOutputVo();
                    memberQueryOutputVo.setId(jo.getInt("id"));
                    memberQueryOutputVo.setFullName(jo.getString("fullName"));
                    memberQueryOutputVo.setMemberId(jo.getInt("id"));
                    list.add(memberQueryOutputVo);
                }
            }
            map.put("success", responseVo.isSuccess());
            map.put("msg", responseVo.getMsg());
            map.put("data", list);
            return map;
        }
    }

}
