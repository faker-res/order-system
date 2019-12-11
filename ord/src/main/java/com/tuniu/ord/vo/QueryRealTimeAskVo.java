package com.tuniu.ord.vo;

import java.util.List;

public class QueryRealTimeAskVo {
    private Integer productId;
    private List<String> departDates;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public List<String> getDepartDates() {
        return departDates;
    }

    public void setDepartDates(List<String> departDates) {
        this.departDates = departDates;
    }

}
