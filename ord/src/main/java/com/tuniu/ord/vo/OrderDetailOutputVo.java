/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月26日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhairongping
 *
 */
public class OrderDetailOutputVo {
    private OrderHeadInfoVo orderHeadInfo;
    private List<OrderBasicStatisticsVo> orderBasicStatistics = new ArrayList<OrderBasicStatisticsVo>();

    public OrderHeadInfoVo getOrderHeadInfo() {
        return orderHeadInfo;
    }

    public void setOrderHeadInfo(OrderHeadInfoVo orderHeadInfo) {
        this.orderHeadInfo = orderHeadInfo;
    }

    public List<OrderBasicStatisticsVo> getOrderBasicStatistics() {
        return orderBasicStatistics;
    }

    public void setOrderBasicStatistics(List<OrderBasicStatisticsVo> orderBasicStatistics) {
        this.orderBasicStatistics = orderBasicStatistics;
    }

}
