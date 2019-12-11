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
 * 【简化版下单入参】
 * 
 * @author zhairongping
 *
 */
public class PlaceOrderInputVo {
    private List<SimpleOrderInfoVo> orderInfoList = new ArrayList<SimpleOrderInfoVo>();
    /**
     * 操作者邮箱地址
     */
    private String recipientEmail;

    public List<SimpleOrderInfoVo> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<SimpleOrderInfoVo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

}
