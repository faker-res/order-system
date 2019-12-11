/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 *
 */
public class ConfirmRequestQueryVo {
    private Integer tuniuOrderId;
    private Integer requirementId;

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

}
