/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月1日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * @author zhairongping
 *
 */
public class ProductLineIdOuterVo {
    private List<ProductLineIdVo> productLineIds;

    /**
     * @return the productLineIds
     */
    public List<ProductLineIdVo> getProductLineIds() {
        return productLineIds;
    }

    /**
     * @param productLineIds
     *            the productLineIds to set
     */
    public void setProductLineIds(List<ProductLineIdVo> productLineIds) {
        this.productLineIds = productLineIds;
    }

}
