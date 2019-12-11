/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import com.tuniu.ord.common.task.ManualTaskVo;

/**
 * @author zhoujie8
 * 
 */
public interface ManualFinishSignService {

    /**
     * @param manualOrderId
     */
    ManualTaskVo finishSign(Integer manualOrderId);

}
