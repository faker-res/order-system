/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu May 19 14:51:59 CST 2016
 * Description:
 */
package com.tuniu.ord.domain;

/**
 * Config config
 */
public class Config extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * ������� config.key_name
     */
    private String keyName;

    /**
     * ����ֵ json config.value_data
     */
    private String valueData;

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getValueData() {
        return valueData;
    }

    public void setValueData(String valueData) {
        this.valueData = valueData;
    }

}