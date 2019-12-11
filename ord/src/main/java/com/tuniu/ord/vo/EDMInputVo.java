/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年7月18日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

import com.tuniu.ord.common.constant.Constants;

/**
 * @author zhairongping
 *
 */
public class EDMInputVo {
    private String systemCode = Constants.systemCode;
    private String businessId = Constants.businessId;
    private String senderName = Constants.senderName;
    private List<String> recipientEmails;
    private List<String> ccEmails;
    private List<UrlVo> urls;
    private String subject;
    private String msgText;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public List<String> getRecipientEmails() {
        return recipientEmails;
    }

    public void setRecipientEmails(List<String> recipientEmails) {
        this.recipientEmails = recipientEmails;
    }

    public List<String> getCcEmails() {
        return ccEmails;
    }

    public void setCcEmails(List<String> ccEmails) {
        this.ccEmails = ccEmails;
    }

    public List<UrlVo> getUrls() {
        return urls;
    }

    public void setUrls(List<UrlVo> urls) {
        this.urls = urls;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

}
