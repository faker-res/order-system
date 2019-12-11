/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service;

import java.util.List;

import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.domain.ManualSupplyment;

/**
 * @author zhoujie8
 * 
 */
public interface ManualSupplymentService {

    /**
     * 根据订单id查询订单增补项
     * 
     * @param orderId
     * @return
     * @throws Exception
     */
    public List<ManualSupplyment> querySupplyment(Integer orderId) throws OrdCustomException;

    /**
     * 修改订单增补项
     * 
     * @param supplyment
     * @return
     * @throws Exception
     */
    public void updateSupplyment(ManualSupplyment supplyment) throws OrdCustomException;

    /**
     * 添加订单增补项
     * 
     * @param supplymentList
     */
    public void addSupplyment(ManualSupplyment supplyment) throws OrdCustomException;

    /**
     * 删除订单增补项
     * 
     * @param id
     */
    public void delSupplyment(Integer id) throws OrdCustomException;

    /**
     * 查询订单详情
     * 
     * @param manualOrderId
     * @return
     */
    public String queryPriceDetail(Integer manualOrderId);

}
