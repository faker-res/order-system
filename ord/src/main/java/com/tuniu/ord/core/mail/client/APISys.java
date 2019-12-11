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
public class APISys extends AbstractSys {
    private static final String DEFAULT_TITLE = "【API系统技术支持邮件提醒】";

    public APISys(String content) {
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
        recipientEmails.add("meijie@tuniu.com");
        recipientEmails.add("shiheng@tuniu.com");
        recipientEmails.add("douyanhui@tuniu.com");
        recipientEmails.add("shangpanpan@tuniu.com");
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
        ccEmails.add("zhangerlin@tuniu.com");
        return ccEmails;
    }

}
