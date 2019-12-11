package com.tuniu.ord.domain;

public class ManualOrderOccupy extends BaseManualDomain{
    /**
     * 
     */
    private static final long serialVersionUID = 2746749244024072307L;

    private Integer dOrderId;

    private Integer number;
    
    private Integer adultNum;
    
    private Integer childNum;
    
    /**
     * 状态(0:未占位,1:占位失败,2:占位成功,3:确认成功,4:确认失败)
     */
    private Integer status;

    public Integer getdOrderId() {
        return dOrderId;
    }

    public void setdOrderId(Integer dOrderId) {
        this.dOrderId = dOrderId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the adultNum
     */
    public Integer getAdultNum() {
        return adultNum;
    }

    /**
     * @param adultNum the adultNum to set
     */
    public void setAdultNum(Integer adultNum) {
        this.adultNum = adultNum;
    }

    /**
     * @return the childNum
     */
    public Integer getChildNum() {
        return childNum;
    }

    /**
     * @param childNum the childNum to set
     */
    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }
}