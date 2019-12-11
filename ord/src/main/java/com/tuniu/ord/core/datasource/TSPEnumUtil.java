/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年1月19日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.core.datasource;

import com.tuniu.ord.common.constant.Constants;

/**
 * TSP枚举工具类
 * 
 * @author zhairongping
 *
 */
public enum TSPEnumUtil {
    TUNIU("tuniu", "DOTA"), WZX("wzx", "WZX");
    private String tenantId;
    private String repalceTSPName;

    /**
     * @param tenantId
     * @param repalceTSPName
     */
    private TSPEnumUtil(String tenantId, String repalceTSPName) {
        this.tenantId = tenantId;
        this.repalceTSPName = repalceTSPName;
    }

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

    /**
     * @return the repalceTSPName
     */
    public String getRepalceTSPName() {
        return repalceTSPName;
    }

    /**
     * @param repalceTSPName
     *            the repalceTSPName to set
     */
    public void setRepalceTSPName(String repalceTSPName) {
        this.repalceTSPName = repalceTSPName;
    }

    /**
     * 根据tenantId查看对应的替换TSPName
     * 
     * @param tenantId
     * @return
     */
    private static String getRepalceTSPName(String tenantId) {
        if (null == tenantId) {
            return TSPEnumUtil.TUNIU.getRepalceTSPName();
        }
        for (TSPEnumUtil ttte : TSPEnumUtil.values()) {
            if (ttte.getTenantId().equals(tenantId)) {
                return ttte.getRepalceTSPName();
            }
        }
        return TSPEnumUtil.TUNIU.getRepalceTSPName();
    }

    /**
     * 动态切换TSP
     * 
     * @param tspName
     *            TSPNAME 包含占位符
     * 
     * @return
     */
    public static String changeTSPName(String tspName) {
        String tenantId = DataSourceSwitch.getTenantId();
        return tspName.replace(Constants.REPLACE_SIGN, getRepalceTSPName(tenantId));
    }

}
