/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.constant;

/**
 * 订单增补项类型
 * 
 * @author zhoujie8
 * 
 */
public enum SupplymentTypeEnum {
    PRODUCT_PREFERENCE(1, "产品优惠"), OTHER(2, "其他");
    // 增补类型
    private Integer type;
    // 名称
    private String name;

    SupplymentTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public static String getNameFromType(Integer type) {
        if (type == null) {
            return null;
        }

        for (SupplymentTypeEnum en : SupplymentTypeEnum.values()) {
            if (en.getType() == type) {
                return en.getName();
            }
        }

        return null;
    }

}
