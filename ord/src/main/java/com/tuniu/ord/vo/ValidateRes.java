/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月2日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhairongping
 *
 */
public class ValidateRes {
    private final static boolean DEFAULT_SUCCESS = true;

    /* 数据迁移参数校验成功的标志 */
    private boolean success = DEFAULT_SUCCESS;
    /* 存储数据迁移失败信息 */
    private List<String> data = new ArrayList<String>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

}
