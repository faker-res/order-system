/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 【数据迁移统计日志】
 * 
 * @author zhairongping
 *
 */
public class StatisticsLog {
    private final static boolean DEFAULT_SUCCESS = false;
    /* 途牛订单号 */
    private Integer tuniuOrderId;
    /* D类销售单号 */
    private Integer dOrderId;
    /* 操作结果 */
    private boolean success = DEFAULT_SUCCESS;
    /* 操作步骤 */
    private List<String> steps = new ArrayList<String>();

    public Integer getTuniuOrderId() {
        return tuniuOrderId;
    }

    public void setTuniuOrderId(Integer tuniuOrderId) {
        this.tuniuOrderId = tuniuOrderId;
    }

    public Integer getdOrderId() {
        return dOrderId;
    }

    public void setdOrderId(Integer dOrderId) {
        this.dOrderId = dOrderId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    /**
     * 转化成字符串
     * 
     * @param steps
     * @return
     */
    public String showSteps(List<String> steps) {
        StringBuffer sb = new StringBuffer();
        Iterator<String> iter = steps.iterator();
        while (iter.hasNext()) {
            String str = iter.next();
            sb.append(str + "      ");
        }
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "{\"tuniuOrderId\":" + this.tuniuOrderId + ",\"dOrderId\":" + this.dOrderId + ",\"success\":" + success
                + ",\"steps\":" + showSteps(steps) + "}";
    }

}
