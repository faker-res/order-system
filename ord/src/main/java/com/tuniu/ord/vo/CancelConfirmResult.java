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
 * 【取消确认日志记录】
 * 
 * @author zhairongping
 *
 */
public class CancelConfirmResult {

    /* 核损方案号 */
    private Integer agencyLossSchemeId;

    /* 记录每个批次处理结果 */
    private List<CancelSubConfirmResult> roundList = new ArrayList<CancelSubConfirmResult>();

    public Integer getAgencyLossSchemeId() {
        return agencyLossSchemeId;
    }

    public void setAgencyLossSchemeId(Integer agencyLossSchemeId) {
        this.agencyLossSchemeId = agencyLossSchemeId;
    }

    public List<CancelSubConfirmResult> getRoundList() {
        return roundList;
    }

    public void setRoundList(List<CancelSubConfirmResult> roundList) {
        this.roundList = roundList;
    }

}
