/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月7日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.domain.ManualContract;
import com.tuniu.ord.persistence.ManualContractMapper;
import com.tuniu.ord.service.ManualContractService;

/**
 * @author zhoujie8
 * 
 */
@Service
public class ManualContractServiceImpl implements ManualContractService {
    private static final Logger logger = LoggerFactory.getLogger(ManualContractServiceImpl.class);

    @Resource
    private ManualContractMapper manualContractMapper;

    /*
     * 添加合同
     */
    @Override
    public void addManualContract(ManualContract manualContract) throws OrdCustomException {
        BaseDomainUtil.setBasePropertyValue(manualContract);
        manualContractMapper.insertSelective(manualContract);
    }

    /*
     * 查询合同
     */
    @Override
    public List<ManualContract> queryContractList(Integer manualOrderId) throws OrdCustomException {
        if (manualOrderId == null) {
            return null;
        }

        return manualContractMapper.selectByManualOrderId(manualOrderId);
    }

    /*
     * (non-Javadoc)
     */
    @Override
    public void delContractById(Integer id) throws OrdCustomException {
        manualContractMapper.deleteByPrimaryKey(id);
    }

}
