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
public class TuniuInfo {
    private Integer requirementId;
    private Integer tuniuSerialId;
    private Integer tuniuOrderId;
    private String name;
    private String tel;
    private String fax;
    private String contact;
    private Integer productId;
    private String productName;

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public Integer getTuniuSerialId() {
        return tuniuSerialId;
    }

    public void setTuniuSerialId(Integer tuniuSerialId) {
        this.tuniuSerialId = tuniuSerialId;
    }

    public Integer getTuniuOrderId() {
        return tuniuOrderId;
    }

    public void setTuniuOrderId(Integer tuniuOrderId) {
        this.tuniuOrderId = tuniuOrderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
