package com.tuniu.ord.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.domain.ManualContact;
import com.tuniu.ord.persistence.ManualContactMapper;
import com.tuniu.ord.service.ManualContactService;

@Service
public class ManualContactServiceImpl implements ManualContactService {

    @Resource
    private ManualContactMapper contactMapper;

    @Override
    public void saveContact(ManualContact record) {
        if (record == null) {
            throw new IllegalArgumentException("manual contact is null");
        }
        BaseDomainUtil.setBasePropertyValue(record);
        if (record.getId() == null) {
            contactMapper.insertSelective(record);
        } else {
            contactMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public void removeContact(ManualContact record) {
        if (record == null || record.getId() == null) {
            // do nothing if record is null;
            return;
        }
        contactMapper.deleteByPrimaryKey(record.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ManualContactService#queryContactList(java.lang.Integer)
     */
    @Override
    public List<ManualContact> queryContactList(Integer manualOrderId) {
        return contactMapper.selectByManualOrderId(manualOrderId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.ManualContactService#delContact(java.lang.Integer)
     */
    @Override
    public void delContact(Integer id) {
        if (id == null)
            return;

        contactMapper.deleteByPrimaryKey(id);
    }

}
