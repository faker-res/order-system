/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月7日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 * 【产品品类】
 * 
 * @author zhairongping
 *
 */
public enum ProductCategoryEnum {
    PRODUCT_CATAGORY(1, "跟团游"), PRODUCT_SUB_CATAGORY(0, "常规跟团");

    private Integer key;
    private String name;

    private ProductCategoryEnum(Integer key, String name) {
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
