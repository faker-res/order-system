/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年4月12日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 * 库存类型 1-控位 2-FS 3-现询
 * 
 * @author zhairongping
 *
 */
public enum StockTypeEnum {

    Position_Control(1, "控位"), Free_Sale(2, "FS"), Inquiry(3, "现询");

    private int key;
    private String value;

    /**
     * @param key
     * @param value
     */
    private StockTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

}
