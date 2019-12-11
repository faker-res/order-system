/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月9日                                                      
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
import com.tuniu.ord.vo.createOrder.ContactQueryVo;
import com.tuniu.ord.vo.createOrder.DMSQueryBaseInputVo;
import com.tuniu.ord.vo.createOrder.MemberQueryInputVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 查询对接人信息
 * 
 * @author zhairongping
 *
 */
@Service(value = "queryContactPersonService")
public class QueryContactPersonDMSCommonServiceImpl implements DMSCommonService {

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.DMSCommonService#provideDMSService(com.tuniu.ord.vo.createOrder.DMSQueryBaseInputVo)
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
        String result = RestUtil.execute(SystemInitParameter.queryMemberContacts, SystemConstants.HTTP_GET,
                JsonUtil.toString(memberQueryInputVo));
        if (null == result || "".equals(result)) {
            map.put("success", false);
            map.put("msg", "调用查询对接人信息接口异常");
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

            JSONObject jb = JSONObject.fromObject(JsonUtil.toString(responseVo.getData()));
            List<ContactQueryVo> list = new ArrayList<ContactQueryVo>();
            JSONArray array = jb.getJSONArray("rows");
            int size = array.size();
            for (int i = 0; i < size; i++) {
                ContactQueryVo contact = new ContactQueryVo();
                JSONObject job = array.getJSONObject(i);
                contact.setMemberId(memberQueryInputVo.getMemberId());
                contact.setName(job.getString("name"));
                contact.setAppellation(1);

                JSONArray subArray = job.getJSONArray("contactWayList");
                int subSize = subArray.size();
                for (int j = 0; j < subSize; j++) {
                    JSONObject subJob = subArray.getJSONObject(j);
                    if (subJob.getInt("type") == 0) {
                        contact.setPhone(subJob.getString("detailWay"));
                    }
                    if (subJob.getInt("type") == 1) {
                        contact.setTel(subJob.getString("detailWay"));
                    }
                    if (subJob.getInt("type") == 2) {
                        contact.setFax(subJob.getString("detailWay"));
                    }
                    if (subJob.getInt("type") == 3) {
                        contact.setEmail(subJob.getString("detailWay"));
                    }
                }
                list.add(contact);
            }
            map.put("success", responseVo.isSuccess());
            map.put("msg", responseVo.getMsg());
            map.put("data", list);
            return map;
        }
    }

}
