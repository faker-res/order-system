/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月11日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.vo.createOrder.ContactQueryVo;

/**
 * @author zhairongping
 *
 */
public interface ContactDMSService {

    /**
     * 保存联系人
     * 
     * @param input
     */
    void saveContact(ContactQueryVo input);

    /**
     * 删除联系人
     * 
     * @param input
     */
    void delContact(ContactQueryVo input);

    /**
     * 修改联系人
     * 
     * @param input
     */
    void updContact(ContactQueryVo input);

    /**
     * 查询联系人
     * 
     * @param input
     * @return
     */
    List<ContactQueryVo> getContacts(ContactQueryVo input);

}
