/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月17日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.enums;

/**
 * 【迁移订单的状态枚举】
 * 
 * @author zhairongping
 *
 */
public enum TransferOrderStatusEnum {
    TRANSFERING(100001, "迁移中"), TRANSFERSUCCESS(100002, "迁移成功"), TRANSFERFAILED(100003, "迁移失败");
    private Integer key;
    private String name;

    private TransferOrderStatusEnum(Integer key, String name) {
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
