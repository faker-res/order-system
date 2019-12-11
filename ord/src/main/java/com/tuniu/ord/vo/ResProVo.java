/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年4月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import com.tuniu.ord.vo.common.BaseVo;

/**
 * @author zhairongping
 *
 */
public class ResProVo extends BaseVo {
    /**
     * A资源编号
     */
    private Long resId;
    /**
     * 产品编号
     */
    private Integer productId;
    
    /**
     * 升级方案编号
     */
    private Integer tourUpgradeId;

    /**
     * @return the resId
     */
    public Long getResId() {
        return resId;
    }

    /**
     * @param resId
     *            the resId to set
     */
    public void setResId(Long resId) {
        this.resId = resId;
    }

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

    public Integer getTourUpgradeId() {
        return tourUpgradeId;
    }

    public void setTourUpgradeId(Integer tourUpgradeId) {
        this.tourUpgradeId = tourUpgradeId;
    }

}
