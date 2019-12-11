/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年1月18日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo.common;

import com.tuniu.ord.core.datasource.DataSourceSwitch;

/**
 * @author zhairongping
 *
 */
public class BaseVo {
    private String tenantId;

    /**
     * @return the tenantId
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId
     *            the tenantId to set
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public static void initTenantId(BaseVo baseVo) {
        baseVo.setTenantId(DataSourceSwitch.getTenantId());
    }

}
