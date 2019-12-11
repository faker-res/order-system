/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年7月27日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 * 渠道分类
 * 
 * @author douyanhui
 *
 */
public enum ChannelClassify {
    DISTRIBUTION("distribution", 1), SELFSALE("selfSale", 0), AGENCY("agency", 2);
    private String key;
    private Integer value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    private ChannelClassify(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public static ChannelClassify getEnumByKey(String key) {
        if (key == null) {
            return null;
        }
        ChannelClassify[] values = ChannelClassify.values();
        for (ChannelClassify channelClassify : values) {
            if (channelClassify.getKey().equals(key)) {
                return channelClassify;
            }
        }
        return null;
    }
}
