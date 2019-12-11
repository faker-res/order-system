package com.tuniu.ord.vo;

import java.util.List;

import com.tuniu.ord.vo.common.BaseVo;

public class AddTouristsVo extends BaseVo {
    
    private Integer manualOrderId;
    
    private List<ManualTouristVo> tourists;

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

    /**
     * @return the tourists
     */
    public List<ManualTouristVo> getTourists() {
        return tourists;
    }

    /**
     * @param tourists the tourists to set
     */
    public void setTourists(List<ManualTouristVo> tourists) {
        this.tourists = tourists;
    }
    
}
