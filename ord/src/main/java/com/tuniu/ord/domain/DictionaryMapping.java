/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu May 19 14:53:16 CST 2016
 * Description:
 */
package com.tuniu.ord.domain;

/**
 * DictionaryMapping dictionary_mapping
 */
public class DictionaryMapping extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * �ֵ��� dictionary_mapping.dicdata_code
     */
    private String dicdataCode;

    /**
     * ӳ��ϵͳ��� dictionary_mapping.mapping_sys
     */
    private String mappingSys;

    /**
     * ӳ����� dictionary_mapping.mapping_name
     */
    private String mappingName;

    public String getDicdataCode() {
        return dicdataCode;
    }

    public void setDicdataCode(String dicdataCode) {
        this.dicdataCode = dicdataCode;
    }

    public String getMappingSys() {
        return mappingSys;
    }

    public void setMappingSys(String mappingSys) {
        this.mappingSys = mappingSys;
    }

    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mappingName) {
        this.mappingName = mappingName;
    }

}