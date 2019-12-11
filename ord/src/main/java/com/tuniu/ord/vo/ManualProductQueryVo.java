package com.tuniu.ord.vo;

import com.tuniu.ord.vo.common.BaseVo;

public class ManualProductQueryVo extends BaseVo{
    private Integer productId;
    private String productName;
    private String departDate;
    private Integer destCategoryId;
    private Integer firstDestGroupId;
    private Integer secDestGroupId;
    private Integer destId;
    private Integer memberId;
    private boolean onlyCanOrderPrd;
    private int start;
    private int limit;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public Integer getDestCategoryId() {
        return destCategoryId;
    }

    public void setDestCategoryId(Integer destCategoryId) {
        this.destCategoryId = destCategoryId;
    }

    public Integer getFirstDestGroupId() {
        return firstDestGroupId;
    }

    public void setFirstDestGroupId(Integer firstDestGroupId) {
        this.firstDestGroupId = firstDestGroupId;
    }

    public Integer getSecDestGroupId() {
        return secDestGroupId;
    }

    public void setSecDestGroupId(Integer secDestGroupId) {
        this.secDestGroupId = secDestGroupId;
    }

    public Integer getDestId() {
        return destId;
    }

    public void setDestId(Integer destId) {
        this.destId = destId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public boolean isOnlyCanOrderPrd() {
        return onlyCanOrderPrd;
    }

    public void setOnlyCanOrderPrd(boolean onlyCanOrderPrd) {
        this.onlyCanOrderPrd = onlyCanOrderPrd;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
