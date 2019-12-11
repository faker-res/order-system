/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月7日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.domain.ManualContract;

/**
 * @author zhoujie8
 * 
 */
public interface ManualContractService {

    /**
     * @param manualContract
     */
    public void addManualContract(ManualContract manualContract) throws OrdCustomException;

    /**
     * @param orderId
     * @return
     */
    public List<ManualContract> queryContractList(Integer orderId) throws OrdCustomException;

    /**
     * @param id
     */
    public void delContractById(Integer id) throws OrdCustomException;

}
