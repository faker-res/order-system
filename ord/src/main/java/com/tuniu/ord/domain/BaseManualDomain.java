package com.tuniu.ord.domain;

public class BaseManualDomain extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = -2264243554583802007L;
    
    private Integer manualOrderId;

    /**
     * @return the manualOrderId
     */
    public Integer getManualOrderId() {
        return manualOrderId;
    }

    /**
     * @param manualOrderId the manualOrderId to set
     */
    public void setManualOrderId(Integer manualOrderId) {
        this.manualOrderId = manualOrderId;
    }
}
