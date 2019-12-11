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
public class OrderInfoQueryOutputVo {
    private List<OrderInfoVo> orderList;

    public List<OrderInfoVo> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderInfoVo> orderList) {
        this.orderList = orderList;
    }
}
