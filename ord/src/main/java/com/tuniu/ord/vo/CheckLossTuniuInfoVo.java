package com.tuniu.ord.vo;

import java.io.Serializable;

public class CheckLossTuniuInfoVo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 9154950821141767620L;

    /**
     * 需求id
     */
    private Integer requirementId;

    /**
     * 途牛流水号
     */
    private Integer tuniuSerialId;

    /**
     * 途牛订单号
     */
    private Integer tuniuOrderId;

    /**
     * 核损有效期
     */
    private String effectiveTime;

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

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

}
