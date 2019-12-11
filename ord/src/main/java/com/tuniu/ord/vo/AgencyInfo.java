/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

/**
 * @author zhairongping
 *
 */
public class AgencyInfo {
    private String agencyProductId;
    private Integer agencyId;
    private String agencyName;
    private String agencyTel;
    private String agencyFax;
    private String agencyContact;

    public String getAgencyProductId() {
        return agencyProductId;
    }

    public void setAgencyProductId(String agencyProductId) {
        this.agencyProductId = agencyProductId;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyTel() {
        return agencyTel;
    }

    public void setAgencyTel(String agencyTel) {
        this.agencyTel = agencyTel;
    }

    public String getAgencyFax() {
        return agencyFax;
    }

    public void setAgencyFax(String agencyFax) {
        this.agencyFax = agencyFax;
    }

    public String getAgencyContact() {
        return agencyContact;
    }

    public void setAgencyContact(String agencyContact) {
        this.agencyContact = agencyContact;
    }

}
