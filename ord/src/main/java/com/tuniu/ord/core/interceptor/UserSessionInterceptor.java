/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年4月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.core.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.util.UserSessionUtil;
import com.tuniu.ord.vo.UserVo;

/**
 * 获取用户ajax请求的uid和nickname并设置到ThreadLocal中
 * 
 * @author fangzhongwei
 * 
 */
public class UserSessionInterceptor extends HandlerInterceptorAdapter {
    private List<String> uncheckUrls;

    public List<String> getUncheckUrls() {
        return uncheckUrls;
    }

    public void setUncheckUrls(List<String> uncheckUrls) {
        this.uncheckUrls = uncheckUrls;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (uncheckUrls.contains(request.getRequestURI())) {
            return true;
        }

        Map<String, Object> paramMap = (Map<String, Object>) request.getAttribute(Constants.AJAX_PARAMETER);

        if (null != paramMap.get("uid") && null != paramMap.get("nickname")) {
            UserVo userVo = new UserVo();
            // userVo.setUid(Integer.valueOf((String) (paramMap.get("uid"))).intValue());
            userVo.setUid(Integer.valueOf(paramMap.get("uid").toString()).intValue());
            userVo.setNickname((String) (paramMap.get("nickname")));

            UserSessionUtil.set(userVo);
        }

        return true;
    }
}
