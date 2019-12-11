/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.proxy;

import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.core.mail.client.ORDMailClient;
import com.tuniu.ord.vo.ConfirmFeedBackInputVo;
import com.tuniu.ord.vo.ResponseVo;

/**
 * 【API代理类】
 * 
 * @author zhairongping
 *
 */
@Service
public class APIProxy {

    /**
     * 调用API确认反馈
     * 
     * @param confirmFeedBackInputVo
     * @return
     */
    public ResponseVo confirmFeedBack(ConfirmFeedBackInputVo confirmFeedBackInputVo) {
        ResponseVo responseVo = new ResponseVo();
        String clientData = JsonUtil.toString(confirmFeedBackInputVo);
        String result = RestUtil.executeToAPI(SystemInitParameter.confirmFeedBackURL, SystemConstants.HTTP_POST, clientData);
        if (result == null || "".equals(result)) {
            String title = "【API技术支持邮件通知】";
            StringBuffer sb = new StringBuffer();
            sb.append("【调用API确认反馈接口异常:");
            sb.append("API的确认反馈请求地址:" + SystemInitParameter.confirmFeedBackURL + ";");
            sb.append("API的确认反馈请求参数:" + JsonUtil.toString(confirmFeedBackInputVo) + ";解决方案:API技术人员重新执行该接口,确保API确认反馈成功】");
            ORDMailClient.sendMail(title, sb.toString());
            responseVo.setSuccess(false);
            responseVo.setMsg("调用API确认反馈失败");
        } else {
            responseVo = JsonUtil.toBean(result, ResponseVo.class);
        }
        return responseVo;
    }
}
