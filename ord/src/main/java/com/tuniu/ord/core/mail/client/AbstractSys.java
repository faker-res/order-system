/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.core.mail.client;

/**
 * @author zhairongping
 *
 */
public abstract class AbstractSys implements ISystem {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
