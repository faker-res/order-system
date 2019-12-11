/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年8月16日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.List;

/**
 * @author zhairongping
 *
 */
public class QueryRelationsIdOutputVo {
    private Integer count;
    private List<DPSDeptInfo> rows;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<DPSDeptInfo> getRows() {
        return rows;
    }

    public void setRows(List<DPSDeptInfo> rows) {
        this.rows = rows;
    }

}
