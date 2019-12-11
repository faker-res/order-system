/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月2日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import com.tuniu.ord.vo.SimpleOrderInfoVo;
import com.tuniu.ord.vo.TransferedAOrderIdsInputAndOutputVo;

/**
 * @author zhairongping
 *
 */
public interface SimpleConfirmService {
    /**
     * 执行数据迁移任务
     * 
     * @param simpleOrderInfoVo
     * @param responseVo
     */
    public void savePlaceOrder(SimpleOrderInfoVo simpleOrderInfoVo);

    /**
     * 查询数据迁移成功的A类订单
     * 
     * @param aOrderIds
     * @return
     */
    public void getTransferedAOrderIds(TransferedAOrderIdsInputAndOutputVo transferedAOrderIdsInputAndOutputVo);

}
