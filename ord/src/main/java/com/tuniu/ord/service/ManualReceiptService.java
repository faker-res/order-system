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
import com.tuniu.ord.domain.ManualReceipt;

/**
 * @author zhoujie8
 * 
 */
public interface ManualReceiptService {

    /**
     * @param manualReceipt
     */
    void addManualReceipt(ManualReceipt manualReceipt) throws OrdCustomException;

    /**
     * @param id
     */
    void updateManualReceipt(ManualReceipt manualReceipt) throws OrdCustomException;

    /**
     * @param id
     */
    void delManualReceipt(Integer id) throws OrdCustomException;

    /**
     * @param orderId
     * @return
     */
    List<ManualReceipt> queryManualReceipt(Integer orderId) throws OrdCustomException;

}
