/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年4月18日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.core.exception.bean;

import com.tuniu.ord.core.exception.ValidationException.ReasonEnum;

/**
 * @author fangzhongwei
 * 
 */
public class ValidationExceptionBean extends ApplicationExceptionBean {

    private static final long serialVersionUID = -8010730529396153864L;

    /**
     * 原因项
     */
    private ReasonEnum reason;

    /**
     * 验证字段名
     */
    private String member;

    public ValidationExceptionBean() {
        super();
    }

    public ValidationExceptionBean(ApplicationExceptionBean bean, ReasonEnum reason, String member) {
        super(bean);
        this.reason = reason;
        this.member = member;
    }

    public ReasonEnum getReason() {
        return reason;
    }

    public void setReason(ReasonEnum reason) {
        this.reason = reason;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

}
