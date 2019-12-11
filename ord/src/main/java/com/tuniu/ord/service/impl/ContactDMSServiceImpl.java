/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月11日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.common.util.BeanUtil;
import com.tuniu.ord.domain.ManualContact;
import com.tuniu.ord.persistence.ManualContactMapper;
import com.tuniu.ord.service.ContactDMSService;
import com.tuniu.ord.vo.createOrder.ContactQueryVo;

/**
 * @author zhairongping
 *
 */
@Service
public class ContactDMSServiceImpl implements ContactDMSService {

    @Resource
    private ManualContactMapper manualContactMapper;

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ContactDMSService#saveContact(com.tuniu.ord.vo.createOrder.ContactQueryVo)
     */
    @Override
    public void saveContact(ContactQueryVo input) {
        ManualContact manualContact = new ManualContact();
        BeanUtil.copyBeanProperties(input, manualContact);
        BaseDomainUtil.setBasePropertyValue(manualContact);
        manualContactMapper.insertSelective(manualContact);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ContactDMSService#delContact(com.tuniu.ord.vo.createOrder.ContactQueryVo)
     */
    @Override
    public void delContact(ContactQueryVo input) {
        ManualContact manualContact = new ManualContact();
        BeanUtil.copyBeanProperties(input, manualContact);
        manualContact.setDelFlag(1);
        manualContactMapper.updateByPrimaryKeySelective(manualContact);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ContactDMSService#updContact(com.tuniu.ord.vo.createOrder.ContactQueryVo)
     */
    @Override
    public void updContact(ContactQueryVo input) {
        ManualContact manualContact = new ManualContact();
        BeanUtil.copyBeanProperties(input, manualContact);
        BaseDomainUtil.setUpdatePropertyValue(manualContact);
        manualContactMapper.updateByPrimaryKeySelective(manualContact);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ContactDMSService#getContacts(com.tuniu.ord.vo.createOrder.ContactQueryVo)
     */
    @Override
    public List<ContactQueryVo> getContacts(ContactQueryVo input) {
        List<ContactQueryVo> list = new ArrayList<ContactQueryVo>();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("manualOrderId", input.getManualOrderId());
        params.put("delFlag", 0);
        params.put("sort", "id");
        params.put("dir", "DESC");
        List<ManualContact> contacts = manualContactMapper.getOrderContacts(params);

        if (contacts != null && contacts.size() > 0) {
            for (ManualContact mc : contacts) {
                ContactQueryVo cqv = new ContactQueryVo();
                BeanUtil.copyBeanProperties(mc, cqv);
                list.add(cqv);
            }
        }
        return list;
    }

}
