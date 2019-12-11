/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月1日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

/**
 * @author zhairongping
 *
 */
public interface BackDoorService {
    /**
     * 判断产品线是否关联部门的规则
     * 
     * @param productLineId
     * @return
     */
    public boolean isRelated(Integer productLineId);
}
