/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年8月16日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhairongping
 *
 */
public class QueryRelationsIdVo {
    /**
     * 产品线
     */
    private Integer productLineId;
    /**
     * 是否表示老产品线
     */
    private boolean oldFlag = false;
    /**
     * 组织类型
     */
    private static List<Integer> relationTypeId = new ArrayList<Integer>();

    static {
        /**
         * 1 销售组织 2 采购组织 3 客服组织 4 访客组织
         */
        relationTypeId.add(new Integer(1));
    }

    public Integer getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(Integer productLineId) {
        this.productLineId = productLineId;
    }

    public boolean isOldFlag() {
        return oldFlag;
    }

    public void setOldFlag(boolean oldFlag) {
        this.oldFlag = oldFlag;
    }

    /**
     * @return the relationTypeId
     */
    public List<Integer> getRelationTypeId() {
        return relationTypeId;
    }

    /**
     * @param relationTypeId
     *            the relationTypeId to set
     */
    public void setRelationTypeId(List<Integer> relationTypeId) {
        this.relationTypeId = relationTypeId;
    }

}
