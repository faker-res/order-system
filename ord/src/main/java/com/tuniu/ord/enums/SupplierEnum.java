/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 * 【供应商】
 * 
 * @author zhairongping
 *
 */
public enum SupplierEnum {
    TUNIU(new Integer(123456), "tuniu");
    private Integer vendorId;
    private String vendorName;

    private SupplierEnum(Integer vendorId, String vendorName) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

}
