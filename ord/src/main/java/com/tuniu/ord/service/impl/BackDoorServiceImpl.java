/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月1日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.service.BackDoorService;
import com.tuniu.ord.vo.DPSDeptInfo;
import com.tuniu.ord.vo.QueryRelationsIdOutputVo;
import com.tuniu.ord.vo.QueryRelationsIdVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * @author zhairongping
 *
 */
@Service
public class BackDoorServiceImpl implements BackDoorService {

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.BackDoorService#isRelated(java.lang.Integer)
     */
    @Override
    public boolean isRelated(Integer productLineId) {
        boolean flag = false;
        QueryRelationsIdVo queryRelationsIdVo = new QueryRelationsIdVo();
        queryRelationsIdVo.setProductLineId(productLineId);
        ResponseVo dpsRes = queryRelationsId(queryRelationsIdVo);
        if (dpsRes.isSuccess() == true) {
            QueryRelationsIdOutputVo queryRelationsIdOutputVo = JsonUtil.toBean(JsonUtil.toString(dpsRes.getData()),
                    QueryRelationsIdOutputVo.class);
            if (null != queryRelationsIdOutputVo) {
                if (CollectionUtils.isNotEmpty(queryRelationsIdOutputVo.getRows())) {
                    for (DPSDeptInfo dept : queryRelationsIdOutputVo.getRows()) {
                        if (Constants.PRODUCT_BRAND_ID.intValue() == dept.getProductBrandId().intValue()
                                && Constants.SALE_STYLE_ID.intValue() == dept.getSaleStyleId().intValue()) {
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 根据产品线id、部门id查询关联关系
     * 
     * @param input
     * @return
     */
    public ResponseVo queryRelationsId(QueryRelationsIdVo input) {
        ResponseVo responseVo = new ResponseVo();
        String data = JsonUtil.toString(input);
        String result = RestUtil.execute("http://10.40.190.25:15605/dps-web/relation/query-relations-id",
                SystemConstants.HTTP_GET, data);
        if (null == result || "".equals(result)) {
            responseVo.setSuccess(false);
            responseVo.setMsg("根据产品线id、部门id查询关联关系接口异常");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }

}
