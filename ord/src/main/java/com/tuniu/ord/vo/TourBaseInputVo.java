/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年4月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

import com.tuniu.ord.vo.common.BaseVo;

/**
 * @author zhairongping
 *
 */
public class TourBaseInputVo extends BaseVo {
    /**
     * 产品ID
     */
    private Integer productId;
    /**
     * 团期
     */
    private List<String> departDate;

    /**
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return the departDate
     */
    public List<String> getDepartDate() {
        return departDate;
    }

    /**
     * @param departDate
     *            the departDate to set
     */
    public void setDepartDate(List<String> departDate) {
        this.departDate = departDate;
    }

}
