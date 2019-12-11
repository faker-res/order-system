package com.tuniu.ord.vo;

import java.math.BigDecimal;
import java.util.List;

public class CheckLossResourceVo {
	
	/**
	 * 对客成人核损尾差
	 */
	private BigDecimal lossAdultCustomerExtra;
	
	/**
	 * 对客儿童核损尾差
	 */
	private BigDecimal lossChildCustomerExtra;
	
	/**
	 * 对客婴儿核损尾差
	 */
	private BigDecimal lossBabyCustomerExtra;
	
	/**
	 * 对供应商成人核损尾差
	 */
	private BigDecimal lossAdultAgencyExtra;
	
	/**
	 * 对供应商儿童核损尾差
	 */
	private BigDecimal lossChildAgencyExtra;
	
	/**
	 * 对供应商婴儿核损尾差
	 */
	private BigDecimal lossBabyAgencyExtra;
	
	/**
	 * 货币
	 */
	private String costCurrencyType;
	
	private List<CheckLossRespDepartVo> departDates;

	public BigDecimal getLossAdultCustomerExtra() {
		return lossAdultCustomerExtra;
	}

	public void setLossAdultCustomerExtra(BigDecimal lossAdultCustomerExtra) {
		this.lossAdultCustomerExtra = lossAdultCustomerExtra;
	}

	public BigDecimal getLossChildCustomerExtra() {
		return lossChildCustomerExtra;
	}

	public void setLossChildCustomerExtra(BigDecimal lossChildCustomerExtra) {
		this.lossChildCustomerExtra = lossChildCustomerExtra;
	}

	public BigDecimal getLossBabyCustomerExtra() {
		return lossBabyCustomerExtra;
	}

	public void setLossBabyCustomerExtra(BigDecimal lossBabyCustomerExtra) {
		this.lossBabyCustomerExtra = lossBabyCustomerExtra;
	}

	public BigDecimal getLossAdultAgencyExtra() {
		return lossAdultAgencyExtra;
	}

	public void setLossAdultAgencyExtra(BigDecimal lossAdultAgencyExtra) {
		this.lossAdultAgencyExtra = lossAdultAgencyExtra;
	}

	public BigDecimal getLossChildAgencyExtra() {
		return lossChildAgencyExtra;
	}

	public void setLossChildAgencyExtra(BigDecimal lossChildAgencyExtra) {
		this.lossChildAgencyExtra = lossChildAgencyExtra;
	}

	public BigDecimal getLossBabyAgencyExtra() {
		return lossBabyAgencyExtra;
	}

	public void setLossBabyAgencyExtra(BigDecimal lossBabyAgencyExtra) {
		this.lossBabyAgencyExtra = lossBabyAgencyExtra;
	}

	public String getCostCurrencyType() {
		return costCurrencyType;
	}

	public void setCostCurrencyType(String costCurrencyType) {
		this.costCurrencyType = costCurrencyType;
	}

	public List<CheckLossRespDepartVo> getDepartDates() {
		return departDates;
	}

	public void setDepartDates(List<CheckLossRespDepartVo> departDates) {
		this.departDates = departDates;
	}
}
