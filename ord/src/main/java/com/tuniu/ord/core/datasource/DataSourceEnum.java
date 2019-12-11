/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年1月18日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.core.datasource;

/**
 * @author zhairongping
 *
 */
public enum DataSourceEnum {
    TUNIU("tuniu", "DOTA_ORD_TUNIU-MASTER-DB", "DOTA_ORD_TUNIU-SLAVE-DB"), WZX("wzx", "DOTA_ORD_WZX-MASTER-DB",
            "DOTA_ORD_WZX-SLAVE-DB");

    private String tenantId;
    private String masterDataSourceBeanId;
    private String slaveDataSourceBeanId;

    /**
     * 判断租户tenantId的合法性
     * 
     * @param tenantId
     * @return
     */
    public static boolean isTrueTenantId(String tenantId) {
        for (DataSourceEnum ds : DataSourceEnum.values()) {
            if (ds.getTenantId().equals(tenantId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据tenantId查看数据源
     * 
     * @param tenantId
     * @return
     */
    public static DataSourceEnum getDataSource(String tenantId) {
        if (null == tenantId) {
            return DataSourceEnum.TUNIU;
        }
        for (DataSourceEnum ds : DataSourceEnum.values()) {
            if (ds.getTenantId().equals(tenantId)) {
                return ds;
            }
        }
        return DataSourceEnum.TUNIU;

    }

    /**
     * @param tenantId
     * @param masterDataSourceBeanId
     * @param slaveDataSourceBeanId
     */
    private DataSourceEnum(String tenantId, String masterDataSourceBeanId, String slaveDataSourceBeanId) {
        this.tenantId = tenantId;
        this.masterDataSourceBeanId = masterDataSourceBeanId;
        this.slaveDataSourceBeanId = slaveDataSourceBeanId;
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
     * @return the masterDataSourceBeanId
     */
    public String getMasterDataSourceBeanId() {
        return masterDataSourceBeanId;
    }

    /**
     * @param masterDataSourceBeanId
     *            the masterDataSourceBeanId to set
     */
    public void setMasterDataSourceBeanId(String masterDataSourceBeanId) {
        this.masterDataSourceBeanId = masterDataSourceBeanId;
    }

    /**
     * @return the slaveDataSourceBeanId
     */
    public String getSlaveDataSourceBeanId() {
        return slaveDataSourceBeanId;
    }

    /**
     * @param slaveDataSourceBeanId
     *            the slaveDataSourceBeanId to set
     */
    public void setSlaveDataSourceBeanId(String slaveDataSourceBeanId) {
        this.slaveDataSourceBeanId = slaveDataSourceBeanId;
    }

}
