/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年8月20日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 * @author zhairongping
 *
 */
public enum ResellFlagEnum {
    IS_RESELL(1, "可以"), IS_NOT_RESELL(0, "不可以");

    private ResellFlagEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    private Integer key;
    private String name;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
