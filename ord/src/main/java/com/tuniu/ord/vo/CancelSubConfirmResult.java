/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 【取消确认单批次日志记录】
 * 
 * @author zhairongping
 *
 */
public class CancelSubConfirmResult {
    /* 核损详情ID */
    private Integer checkLossDetailId;

    /* D类销售单号 */
    private Integer dOrderId;

    /* 操作步骤 */
    private List<String> steps = new ArrayList<String>();

    public Integer getCheckLossDetailId() {
        return checkLossDetailId;
    }

    public void setCheckLossDetailId(Integer checkLossDetailId) {
        this.checkLossDetailId = checkLossDetailId;
    }

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

}
