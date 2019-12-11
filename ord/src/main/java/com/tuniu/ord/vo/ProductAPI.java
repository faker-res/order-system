/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * @author zhairongping
 *
 */
public class ProductAPI {
    private String agencyProductId;
    private String agencyOrderId;
    private Integer agencyOwnerId = 71;
    private String agencyOwnerName = "系统";
    private String remark;
    private List<DepartDateAPI> departDates;

    public String getAgencyProductId() {
        return agencyProductId;
    }

    public void setAgencyProductId(String agencyProductId) {
        this.agencyProductId = agencyProductId;
    }

    public String getAgencyOrderId() {
        return agencyOrderId;
    }

    public void setAgencyOrderId(String agencyOrderId) {
        this.agencyOrderId = agencyOrderId;
    }

    public Integer getAgencyOwnerId() {
        return agencyOwnerId;
    }

    public void setAgencyOwnerId(Integer agencyOwnerId) {
        this.agencyOwnerId = agencyOwnerId;
    }

    public String getAgencyOwnerName() {
        return agencyOwnerName;
    }

    public void setAgencyOwnerName(String agencyOwnerName) {
        this.agencyOwnerName = agencyOwnerName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<DepartDateAPI> getDepartDates() {
        return departDates;
    }

    public void setDepartDates(List<DepartDateAPI> departDates) {
        this.departDates = departDates;
    }

}
