/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月26日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 *
 */
public class AgencyInfoConfirm {
    private Integer agencyId;
    private String agencyOrderId;
    /**
     * 供应商核损方案号
     */
    private Integer agencyLossSchemeId;

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyOrderId() {
        return agencyOrderId;
    }

    public void setAgencyOrderId(String agencyOrderId) {
        this.agencyOrderId = agencyOrderId;
    }

    public Integer getAgencyLossSchemeId() {
        return agencyLossSchemeId;
    }

    public void setAgencyLossSchemeId(Integer agencyLossSchemeId) {
        this.agencyLossSchemeId = agencyLossSchemeId;
    }

}
