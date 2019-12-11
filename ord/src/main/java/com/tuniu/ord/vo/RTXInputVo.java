/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年7月28日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.core.init.SystemInitParameter;

/**
 * @author zhairongping
 * 
 */
/*
 * { "title": "小公举", "orderId": "0", "fromEmail": "rtx@tuniu.com", "fromName": "rtxSys", "content": "冒着风险", "stepId": 1, "uids":
 * [ 12521 ] }
 */
public class RTXInputVo {
    private String title;
    private Integer orderId = new Integer(0);
    private String fromEmail = SystemConstants.FROM_EMAIL;
    private String fromName = SystemConstants.FROM_NAME;
    private String content;
    /** 环境不同,stepId也不同 **/
    private Integer stepId = SystemInitParameter.stepId;
    private List<Integer> uids;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStepId() {
        return stepId;
    }

    public List<Integer> getUids() {
        return uids;
    }

    public void setUids(List<Integer> uids) {
        this.uids = uids;
    }

}
