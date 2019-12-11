/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月21日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 *  1：发起确认 2：发起核损
 */
/**
 * @author zhairongping
 *
 */
public enum OperatorTypeEnum {
    CONFIRM_OPE(1, "发起确认"), LOSS_OPE(2, "发起核损");

    private Integer key;
    private String name;

    /**
     * @param key
     * @param name
     */
    private OperatorTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * @return the key
     */
    public Integer getKey() {
        return key;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

}
