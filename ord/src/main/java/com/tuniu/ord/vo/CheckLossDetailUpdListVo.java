package com.tuniu.ord.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class CheckLossDetailUpdListVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8822679722063432766L;
	
	/**
	 * 是否可再售卖
	 */
	private Byte resellFlag;
	
	/**
     * 每单位成人损失金额（对客） check_loss_detail.loss_adult_customer
     */
    private BigDecimal lossAdultCustomer;
    
    /**
     * 每单位儿童损失金额（对客） check_loss_detail.loss_child_customer
     */
    private BigDecimal lossChildCustomer;

    /**
     * 每单位婴儿损失金额（对客） check_loss_detail.loss_baby_customer
     */
    private BigDecimal lossBabyCustomer;

	public Byte getResellFlag() {
		return resellFlag;
	}

	public void setResellFlag(Byte resellFlag) {
		this.resellFlag = resellFlag;
	}

	public BigDecimal getLossAdultCustomer() {
		return lossAdultCustomer;
	}

	public void setLossAdultCustomer(BigDecimal lossAdultCustomer) {
		this.lossAdultCustomer = lossAdultCustomer;
	}

	public BigDecimal getLossChildCustomer() {
		return lossChildCustomer;
	}

	public void setLossChildCustomer(BigDecimal lossChildCustomer) {
		this.lossChildCustomer = lossChildCustomer;
	}

	public BigDecimal getLossBabyCustomer() {
		return lossBabyCustomer;
	}

	public void setLossBabyCustomer(BigDecimal lossBabyCustomer) {
		this.lossBabyCustomer = lossBabyCustomer;
	}
}
