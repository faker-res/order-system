/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu May 19 15:02:31 CST 2016
 * Description:
 */
package com.tuniu.ord.domain;

/**
 * OrdCommonRemark ord_common_remark
 */
public class OrdCommonRemark extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * ������ ord_common_remark.order_id
     */
    private Integer orderId;

    /**
     * �������� ord_common_remark.memo_type
     */
    private String memoType;

    /**
     * �������� ord_common_remark.memo_content
     */
    private String memoContent;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getMemoType() {
        return memoType;
    }

    public void setMemoType(String memoType) {
        this.memoType = memoType;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

}