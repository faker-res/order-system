/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月11日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.validator.impl;

import com.tuniu.ord.validator.ContactDMSValidator;
import com.tuniu.ord.vo.createOrder.ContactQueryVo;
import com.tuniu.ord.vo.createOrder.ResultInfer;
import com.tuniu.ord.vo.createOrder.ResultInferVo;

/**
 * 联系人校验适配器
 * 
 * @author zhairongping
 *
 */
public abstract class ContactDMSValidatorAdaptor implements ContactDMSValidator {
    private static final ResultInfer DEFAULT_RESULT = new ResultInferVo();

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.validator.ContactDMSValidator#validateSaveContact(com.tuniu.ord.vo.createOrder.ContactQueryVo)
     */
    @Override
    public ResultInfer validateSaveContact(ContactQueryVo input) {
        return DEFAULT_RESULT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.validator.ContactDMSValidator#validateDelContact(com.tuniu.ord.vo.createOrder.ContactQueryVo)
     */
    @Override
    public ResultInfer validateDelContact(ContactQueryVo input) {
        return DEFAULT_RESULT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.validator.ContactDMSValidator#validateuUpdContact(com.tuniu.ord.vo.createOrder.ContactQueryVo)
     */
    @Override
    public ResultInfer validateuUpdContact(ContactQueryVo input) {
        return DEFAULT_RESULT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.validator.ContactDMSValidator#validateGetContacts(com.tuniu.ord.vo.createOrder.ContactQueryVo)
     */
    @Override
    public ResultInfer validateGetContacts(ContactQueryVo input) {
        return DEFAULT_RESULT;
    }

}
