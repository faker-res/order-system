/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年7月27日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 * 【游客类型】
 * 
 * @author zhairongping
 *
 */
public enum TouristTypeEnum {
    ADULT(0, "成人"), CHILD(1, "儿童");
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

    private TouristTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

}
