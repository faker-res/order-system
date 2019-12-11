/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月8日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.createOrder;

import java.util.List;

/**
 * 人工下单参数
 * 
 * @author zhairongping
 *
 */
public class ManualCreateOrderParamInputVo extends OrderBaseParamInputVo {
    /**
     * 联系人
     */
    private List<ContactQueryVo> contacts;
    /**
     * 出游需求
     */
    private RequirementQueryVo requirement;
    /**
     * 订单信息
     */
    private OrderQueryVo orderInfo;

    /**
     * @return the contacts
     */
    public List<ContactQueryVo> getContacts() {
        return contacts;
    }

    /**
     * @param contacts
     *            the contacts to set
     */
    public void setContacts(List<ContactQueryVo> contacts) {
        this.contacts = contacts;
    }

    /**
     * @return the requirement
     */
    public RequirementQueryVo getRequirement() {
        return requirement;
    }

    /**
     * @param requirement
     *            the requirement to set
     */
    public void setRequirement(RequirementQueryVo requirement) {
        this.requirement = requirement;
    }

    /**
     * @return the orderInfo
     */
    public OrderQueryVo getOrderInfo() {
        return orderInfo;
    }

    /**
     * @param orderInfo
     *            the orderInfo to set
     */
    public void setOrderInfo(OrderQueryVo orderInfo) {
        this.orderInfo = orderInfo;
    }

}
