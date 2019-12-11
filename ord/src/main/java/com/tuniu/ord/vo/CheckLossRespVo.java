package com.tuniu.ord.vo;

import java.io.Serializable;
import java.util.List;

public class CheckLossRespVo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 573543781086333600L;

    /**
     * 秘钥
     */
    private String apiKey;

    /**
     * 时间戳
     */
    private String timestamp;

    private List<ProductsVo> products;

    private Integer sessionId;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<ProductsVo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsVo> products) {
        this.products = products;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
}
