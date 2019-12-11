/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月21日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 * 【核损方式】
 * 
 * @author zhairongping
 *
 */
public enum CheckModeEnum {
    AUTOMATIC_MODE(0, "自动核损"), MANUAL_MODE(1, "手工核损");
    private Integer key;
    private String name;

    private CheckModeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

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
