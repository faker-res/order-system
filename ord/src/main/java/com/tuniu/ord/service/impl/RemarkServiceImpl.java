/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tuniu.ord.common.util.BeanUtil;
import com.tuniu.ord.common.util.UserSessionUtil;
import com.tuniu.ord.domain.OrdCommonRemark;
import com.tuniu.ord.persistence.OrdCommonRemarkMapper;
import com.tuniu.ord.service.RemarkService;
import com.tuniu.ord.vo.OrdCommonRemarkVo;

/**
 * @author fangzhongwei
 * 
 */
@Service
public class RemarkServiceImpl implements RemarkService {

    @Resource
    private OrdCommonRemarkMapper ordCommonRemarkMapper;

    @Override
    public List<OrdCommonRemark> getRemarkList(OrdCommonRemarkVo ordCommonRemarkVo) {
        return ordCommonRemarkMapper.selectByFields(ordCommonRemarkVo);
    }

    @Override
    public void saveRemark(OrdCommonRemarkVo ordCommonRemarkVo) {
        OrdCommonRemark record = new OrdCommonRemark();
        BeanUtil.copyBeanProperties(ordCommonRemarkVo, record);
        record.setAddUid(Integer.valueOf(UserSessionUtil.getUid()));
        record.setAddUname(UserSessionUtil.getNickname());
        record.setAddTime(Calendar.getInstance().getTime());
        ordCommonRemarkMapper.insertSelective(record);
    }

    @Override
    public void deleteRemark(OrdCommonRemarkVo ordCommonRemarkVo) {
        ordCommonRemarkMapper.deleteByPrimaryKey(ordCommonRemarkVo);
    }

    @Override
    public int count(OrdCommonRemarkVo ordCommonRemarkVo) {
        return ordCommonRemarkMapper.countByFields(ordCommonRemarkVo);
    }

}
