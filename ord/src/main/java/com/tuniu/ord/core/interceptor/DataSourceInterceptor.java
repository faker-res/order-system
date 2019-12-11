/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年4月18日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.core.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.datasource.DataSourceEnum;
import com.tuniu.ord.core.datasource.DataSourceSwitch;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 获取用户ajax请求的tenantId并设置到ThreadLocal中，供后续操作对应DB使用
 * 
 * @author fangzhongwei
 * 
 */
public class DataSourceInterceptor extends HandlerInterceptorAdapter {
    /**
     * 直接过滤掉的定时任务URL
     */
    private List<String> uncheckUrls;

    public List<String> getUncheckUrls() {
        return uncheckUrls;
    }

    public void setUncheckUrls(List<String> uncheckUrls) {
        this.uncheckUrls = uncheckUrls;
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (uncheckUrls.contains(request.getRequestURI())) {
            return true;
        }

        String parameter = RestUtil.getRestData(request, response);

        if (StringUtils.isNotBlank(parameter)) {
            Map<String, Object> paramMap = JsonUtil.toBean(parameter, Map.class);
            if (null == paramMap || paramMap.get(Constants.TENANT_ID) == null) {
                ResponseVo responseVo = new ResponseVo();
                responseVo.setSuccess(false);
                responseVo.setMsg("【租户tenantId】必传");
                RestUtil.write(response, responseVo);
                return false;
            }

            String tenantId = (String) paramMap.get(Constants.TENANT_ID);
            if (!DataSourceEnum.isTrueTenantId(tenantId)) {
                ResponseVo responseVo = new ResponseVo();
                responseVo.setSuccess(false);
                responseVo.setMsg("【租户tenantId】值不合法");
                RestUtil.write(response, responseVo);
                return false;
            }

            DataSourceEnum ds = DataSourceEnum.getDataSource(tenantId);
            DataSourceSwitch.set(ds.getMasterDataSourceBeanId());
            DataSourceSwitch.setTenantId(tenantId);
            // 后续拦截器使用
            request.setAttribute(Constants.AJAX_PARAMETER, paramMap);
            return true;
        } else {
            DataSourceSwitch.setTenantId(DataSourceEnum.TUNIU.getTenantId());
            DataSourceSwitch.set(DataSourceEnum.TUNIU.getMasterDataSourceBeanId());
            // 后续拦截器使用
            request.setAttribute(Constants.AJAX_PARAMETER, new HashMap<String, Object>());
            return true;
        }
    }

}
