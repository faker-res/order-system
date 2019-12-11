/**
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: 
 * Date: Thu May 19 14:52:34 CST 2016
 * Description:
 */
package com.tuniu.ord.domain;

/**
 * Dictionary dictionary
 */
public class Dictionary extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * �ֵ��루�̶��ģ�����ģ� dictionary.dict_code
     */
    private String dictCode;

    /**
     * �ֵ����� dictionary.dict_name
     */
    private String dictName;

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

}