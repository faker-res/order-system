/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月26日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * 【取消确认入参】
 * 
 * @author zhairongping
 *
 */
public class CancelConfirmInputVo {
    private String apiKey;
    private String sign;
    private String timestamp;
    private int sessionId;
    private TuniuInfoConfirm tuniuInfo;
    private AgencyInfoConfirm agencyInfo;

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

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public TuniuInfoConfirm getTuniuInfo() {
        return tuniuInfo;
    }

    public void setTuniuInfo(TuniuInfoConfirm tuniuInfo) {
        this.tuniuInfo = tuniuInfo;
    }

    public AgencyInfoConfirm getAgencyInfo() {
        return agencyInfo;
    }

    public void setAgencyInfo(AgencyInfoConfirm agencyInfo) {
        this.agencyInfo = agencyInfo;
    }

}
