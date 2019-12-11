/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月8日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.Map;

import com.tuniu.ord.vo.createOrder.DMSQueryBaseInputVo;

/**
 * DMS公共服务接口
 * 
 * @author zhairongping
 *
 */
public interface DMSCommonService {

    /**
     * 提供给订单系统服务通道
     * 
     * @param input
     *            制定服务入参
     * @return 指明提供服务操作结果和操作数据
     */
    Map<String, Object> provideDMSService(DMSQueryBaseInputVo input);

}
