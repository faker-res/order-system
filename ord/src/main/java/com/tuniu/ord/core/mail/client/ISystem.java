/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.core.mail.client;

import java.util.List;

/**
 * @author zhairongping
 *
 */
public interface ISystem {
    /**
     * 邮件标题
     * 
     * @return
     */
    public String getSubject();

    /**
     * 邮件内容
     * 
     * @return
     */
    public String getMsgText();

    /**
     * 邮件收件人
     * 
     * @return
     */
    public List<String> getRecipientEmails();

    /**
     * 邮件抄送人
     * 
     * @return
     */
    public List<String> getCcEmails();
}
