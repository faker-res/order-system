/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年1月13日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.core.datasource;

/**
 * @author zhairongping
 *
 */
public enum MethodEnum {
    GET("get"), COUNT("count"), FIND("find"), QUERY("query");

    private String methodName;

    /**
     * 判断方法是否只读
     * 
     * @param methodName
     * @return
     */
    public static boolean isOnlyRead(String methodName) {
        for (MethodEnum me : MethodEnum.values()) {
            if (methodName.startsWith(me.getMethodName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param methodName
     */
    private MethodEnum(String methodName) {
        this.methodName = methodName;
    }

    /**
     * @return the methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @param methodName
     *            the methodName to set
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}
