/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月25日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * @author fangzhongwei
 * 
 */
public class OrdCommonRemarkVo extends BaseQueryVo {

    private static final long serialVersionUID = -8127086432717316849L;

    private List<Integer> ids;

    private Integer orderId;

    private String memoType;

    private String memoContent;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

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
