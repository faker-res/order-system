/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.core.mail.client;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhairongping
 *
 */
public class PRDSys extends AbstractSys {
    private static final String DEFAULT_TITLE = "【D类产品系统技术支持邮件提醒】";

    public PRDSys(String content) {
        super.setContent(content);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.core.mail.client.ISystem#getSubject()
     */
    @Override
    public String getSubject() {
        return DEFAULT_TITLE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.core.mail.client.ISystem#getMsgText()
     */
    @Override
    public String getMsgText() {
        return super.getContent();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.core.mail.client.ISystem#getRecipientEmails()
     */
    @Override
    public List<String> getRecipientEmails() {
        List<String> recipientEmails = new ArrayList<String>();
        recipientEmails.add("fangzhongwei@tuniu.com");
        recipientEmails.add("zhangzhuo@tuniu.com");
        recipientEmails.add("zhairongping@tuniu.com");
        return recipientEmails;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.core.mail.client.ISystem#getCcEmails()
     */
    @Override
    public List<String> getCcEmails() {
        List<String> ccEmails = new ArrayList<String>();
        ccEmails.add("lingchuanzhi@tuniu.com");
        return ccEmails;
    }

}
