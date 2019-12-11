/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月11日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.validator.impl;

import org.springframework.stereotype.Service;

import com.tuniu.ord.vo.createOrder.ContactQueryVo;
import com.tuniu.ord.vo.createOrder.ResultInfer;
import com.tuniu.ord.vo.createOrder.ResultInferVo;

/**
 * @author zhairongping
 *
 */
@Service
public class ContactDMSValidatorImpl extends ContactDMSValidatorAdaptor {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tuniu.ord.validator.impl.ContactDMSValidatorAdaptor#validateSaveContact(com.tuniu.ord.vo.createOrder.ContactQueryVo)
     */
    @Override
    public ResultInfer validateSaveContact(ContactQueryVo input) {
        ResultInferVo result = new ResultInferVo();
        if (input.getManualOrderId() == null) {
            result.setSuccess(false);
            result.setMsg("订单号不能为空");
            return result;
        }
        if (input.getMemberId() == null) {
            result.setSuccess(false);
            result.setMsg("会员ID不能为空");
            return result;
        }
        if (input.getName() == null || "".equals(input.getName())) {
            result.setSuccess(false);
            result.setMsg("联系人姓名不能为空");
            return result;
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tuniu.ord.validator.impl.ContactDMSValidatorAdaptor#validateDelContact(com.tuniu.ord.vo.createOrder.ContactQueryVo)
     */
    @Override
    public ResultInfer validateDelContact(ContactQueryVo input) {
        ResultInferVo result = new ResultInferVo();
        if (input.getId() == null) {
            result.setSuccess(false);
            result.setMsg("联系人ID不能为空");
            return result;
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tuniu.ord.validator.impl.ContactDMSValidatorAdaptor#validateuUpdContact(com.tuniu.ord.vo.createOrder.ContactQueryVo)
     */
    @Override
    public ResultInfer validateuUpdContact(ContactQueryVo input) {
        ResultInferVo result = new ResultInferVo();
        if (input.getId() == null) {
            result.setSuccess(false);
            result.setMsg("联系人ID不能为空");
            return result;
        }
        if (input.getManualOrderId() == null) {
            result.setSuccess(false);
            result.setMsg("订单号不能为空");
            return result;
        }
        if (input.getMemberId() == null) {
            result.setSuccess(false);
            result.setMsg("会员ID不能为空");
            return result;
        }
        if (input.getName() == null || "".equals(input.getName())) {
            result.setSuccess(false);
            result.setMsg("联系人姓名不能为空");
            return result;
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tuniu.ord.validator.impl.ContactDMSValidatorAdaptor#validateGetContacts(com.tuniu.ord.vo.createOrder.ContactQueryVo)
     */
    @Override
    public ResultInfer validateGetContacts(ContactQueryVo input) {
        ResultInferVo result = new ResultInferVo();
        if (input.getManualOrderId() == null) {
            result.setSuccess(false);
            result.setMsg("订单号不能为空");
            return result;
        }
        return result;
    }
}
