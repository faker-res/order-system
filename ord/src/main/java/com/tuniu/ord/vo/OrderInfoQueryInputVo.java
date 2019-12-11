/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月16日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * @author zhairongping
 *
 */
public class OrderInfoQueryInputVo {
    private List<Integer> orderIds;

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }
}
