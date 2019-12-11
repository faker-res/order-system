/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月11日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.validator;

import com.tuniu.ord.vo.createOrder.ContactQueryVo;
import com.tuniu.ord.vo.createOrder.ResultInfer;

/**
 * 联系人校验器
 * 
 * @author zhairongping
 *
 */
public interface ContactDMSValidator {

    ResultInfer validateSaveContact(ContactQueryVo input);

    ResultInfer validateDelContact(ContactQueryVo input);

    ResultInfer validateuUpdContact(ContactQueryVo input);

    ResultInfer validateGetContacts(ContactQueryVo input);
}
