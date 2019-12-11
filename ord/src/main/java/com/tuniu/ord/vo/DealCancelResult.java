/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月27日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhairongping
 *
 */
public class DealCancelResult {
    /* 供应商核损方案号 */
    private Integer agencyLossSchemeId;

    private List<String> stockResult = new ArrayList<String>();

    private List<String> grpResult = new ArrayList<String>();

    public DealCancelResult() {
        super();
    }

    public DealCancelResult(Integer agencyLossSchemeId) {
        super();
        this.agencyLossSchemeId = agencyLossSchemeId;
    }

    public Integer getAgencyLossSchemeId() {
        return agencyLossSchemeId;
    }

    public void setAgencyLossSchemeId(Integer agencyLossSchemeId) {
        this.agencyLossSchemeId = agencyLossSchemeId;
    }

    public List<String> getStockResult() {
        return stockResult;
    }

    public void setStockResult(List<String> stockResult) {
        this.stockResult = stockResult;
    }

    public List<String> getGrpResult() {
        return grpResult;
    }

    public void setGrpResult(List<String> grpResult) {
        this.grpResult = grpResult;
    }

}
