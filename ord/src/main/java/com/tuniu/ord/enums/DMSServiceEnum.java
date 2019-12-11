/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月8日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 * 会员服务枚举
 * 
 * @author zhairongping
 *
 */
public enum DMSServiceEnum {
    /**
     * 根据公司名称模糊搜索
     */
    QueryByFullNameService("queryByFullNameService"),
    /**
     * 查询对接人信息
     */
    QueryContactPersonService("queryContactPersonService");

    private String serviceName;

    /**
     * @param serviceName
     */
    private DMSServiceEnum(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName
     *            the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
