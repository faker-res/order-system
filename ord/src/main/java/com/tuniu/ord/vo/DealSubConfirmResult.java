/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月26日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 【发起确认单批次日志记录】
 * 
 * @author zhairongping
 *
 */
public class DealSubConfirmResult {
    /**
     * D类销售单号
     */
    private Integer dOrderId;

    /**
     * A资源批次
     */
    private String roundId;

    /* 操作步骤 */
    private List<String> steps = new ArrayList<String>();

    public Integer getdOrderId() {
        return dOrderId;
    }

    public void setdOrderId(Integer dOrderId) {
        this.dOrderId = dOrderId;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getRoundId() {
        return roundId;
    }

    public void setRoundId(String roundId) {
        this.roundId = roundId;
    }

}
