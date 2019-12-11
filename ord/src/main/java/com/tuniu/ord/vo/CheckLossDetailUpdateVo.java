package com.tuniu.ord.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CheckLossDetailUpdateVo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 5902147867085553385L;

    /**
     * 核损编号id
     */
    private Integer checkLossId;

    /**
     * 是否可再售卖
     */
    private List<Byte> resellFlag;

    /**
     * 批次号
     */
    private List<Integer> roundId;

    /**
     * 每单位成人损失金额（对客） check_loss_detail.loss_adult_customer
     */
    private List<BigDecimal> lossAdultCustomer;

    /**
     * 每单位儿童损失金额（对客） check_loss_detail.loss_child_customer
     */
    private List<BigDecimal> lossChildCustomer;

    /**
     * 每单位婴儿损失金额（对客） check_loss_detail.loss_baby_customer
     */
    private List<BigDecimal> lossBabyCustomer;

    /**
     * 核损方案有效期 check_loss_detail.expire_time
     */
    private String expireTime;

    /**
     * 核损备注
     */
    private String remark;

    public List<Byte> getResellFlag() {
        return resellFlag;
    }

    public void setResellFlag(List<Byte> resellFlag) {
        this.resellFlag = resellFlag;
    }

    public List<Integer> getRoundId() {
        return roundId;
    }

    public void setRoundId(List<Integer> roundId) {
        this.roundId = roundId;
    }

    public List<BigDecimal> getLossAdultCustomer() {
        return lossAdultCustomer;
    }

    public void setLossAdultCustomer(List<BigDecimal> lossAdultCustomer) {
        this.lossAdultCustomer = lossAdultCustomer;
    }

    public List<BigDecimal> getLossChildCustomer() {
        return lossChildCustomer;
    }

    public void setLossChildCustomer(List<BigDecimal> lossChildCustomer) {
        this.lossChildCustomer = lossChildCustomer;
    }

    public List<BigDecimal> getLossBabyCustomer() {
        return lossBabyCustomer;
    }

    public void setLossBabyCustomer(List<BigDecimal> lossBabyCustomer) {
        this.lossBabyCustomer = lossBabyCustomer;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCheckLossId() {
        return checkLossId;
    }

    public void setCheckLossId(Integer checkLossId) {
        this.checkLossId = checkLossId;
    }

}
