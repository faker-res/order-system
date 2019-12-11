/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年10月12日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据销售单号查询出库信息出参
 * 
 * @author zhairongping
 *
 */
public class StockOutOutputVo {
    private Integer count;

    private List<StockOutInfo> rows = new ArrayList<StockOutInfo>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<StockOutInfo> getRows() {
        return rows;
    }

    public void setRows(List<StockOutInfo> rows) {
        this.rows = rows;
    }
}
