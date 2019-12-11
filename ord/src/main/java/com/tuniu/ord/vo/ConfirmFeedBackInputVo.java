/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * 【确认反馈入参】
 * 
 * @author zhairongping
 *
 */
public class ConfirmFeedBackInputVo {
    private String apiKey;
    private String sign;
    private String timestamp;
    private Integer sessionId;
    private boolean success;
    private List<ProductAPI> products;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ProductAPI> getProducts() {
        return products;
    }

    public void setProducts(List<ProductAPI> products) {
        this.products = products;
    }

}
