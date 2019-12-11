/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Mon Oct 17 10:26:20 CST 2016
 * Description:
 */
package com.tuniu.ord.persistence;

import java.util.List;

import com.tuniu.ord.domain.OrdTransferOrder;

public interface OrdTransferOrderMapper {
    /**
     * insertSelective
     * 
     * @param OrdTransferOrder
     *            record
     * @return int
     */
    int insertSelective(OrdTransferOrder record);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param OrdTransferOrder
     *            record
     * @return int
     */
    int updateByPrimaryKeySelective(OrdTransferOrder record);

    /**
     * 查询已迁移的A订单
     * 
     * @param aOrderIds
     * @return
     */
    public List<Integer> getTransferedAOrderIdLists(List<Integer> aOrderIds);
}