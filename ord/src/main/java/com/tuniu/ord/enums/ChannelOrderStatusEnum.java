/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年11月21日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 * 0:未处理 1:已处理
 */
/**
 * @author zhairongping
 *
 */
public enum ChannelOrderStatusEnum {
    DEALING(0, "未处理"), DEALED(1, "已处理");

    private Integer key;
    private String name;

    /**
     * @param key
     * @param name
     */
    private ChannelOrderStatusEnum(Integer key, String name) {
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
