/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月2日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 【数据迁移成功A类订单的入参和出参】
 * 
 * @author zhairongping
 *
 */
public class TransferedAOrderIdsInputAndOutputVo {
    private List<Integer> orderIds = new ArrayList<Integer>();

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }

}
