package com.tuniu.ord.vo;

import java.io.Serializable;

public class CheckLossAgencyInfoVo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 829775117872767997L;

    /**
     * 供应商订单号
     */
    private String agencyOrderId;

    /**
     * 供应商id
     */
    private String agencyId;

    /**
     * 核损id
     */
    private Integer agencyLossSchemeId;

    /**
     * 核损有效期
     */
    private String effectiveTime;

    /**
     * 核损备注
     */
    private String remark;

    /**
     * 核损反馈人id
     */
    private Integer agencyOwnerId;

    /**
     * 核损反馈人姓名
     */
    private String agencyOwnerName;

    public String getAgencyOrderId() {
        return agencyOrderId;
    }

    public void setAgencyOrderId(String agencyOrderId) {
        this.agencyOrderId = agencyOrderId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public Integer getAgencyLossSchemeId() {
        return agencyLossSchemeId;
    }

    public void setAgencyLossSchemeId(Integer agencyLossSchemeId) {
        this.agencyLossSchemeId = agencyLossSchemeId;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
