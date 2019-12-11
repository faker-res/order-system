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
 * 【发起确认日志记录】
 * 
 * @author zhairongping
 *
 */
public class DealConfirmResult {
    /**
     * 途牛订单号
     */
    private Integer tuniuOrderId;

    /**
     * 需求id
     */
    private Integer requirementId;

    /**
     * 记录每个批次处理结果
     */
    private List<DealSubConfirmResult> roundList = new ArrayList<DealSubConfirmResult>();

    /**
     * 记录异步反馈结果
     */
    private String feedBack;

    public DealConfirmResult(Integer tuniuOrderId, Integer requirementId) {
        super();
        this.tuniuOrderId = tuniuOrderId;
        this.requirementId = requirementId;
    }

    public DealConfirmResult() {
        super();
    }

    public Integer getTuniuOrderId() {
        return tuniuOrderId;
    }

    public void setTuniuOrderId(Integer tuniuOrderId) {
        this.tuniuOrderId = tuniuOrderId;
    }

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public List<DealSubConfirmResult> getRoundList() {
        return roundList;
    }

    public void setRoundList(List<DealSubConfirmResult> roundList) {
        this.roundList = roundList;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

}
