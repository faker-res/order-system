package com.tuniu.ord.vo;

import java.io.Serializable;
import java.util.List;

public class CheckLossReqVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2620198248021957520L;

	/**
	 * 分配给供应商的唯一身份标识
	 */
	private String apiKey;
	
	/**
	 * 请求签名，生成规则参见签名机制
	 */
	private String sign;
	
	/**
	 * 时间戳
	 */
	private String timestamp;
	
	/**
	 * 会话Id
	 */
	private int sessionId;
	
	/**
	 * products
	 */
	private List<ProductsVo> products;
	
	public List<ProductsVo> getProducts() {
		return products;
	}

	public void setProducts(List<ProductsVo> products) {
		this.products = products;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	
	
}
