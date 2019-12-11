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
 * 根据切位单号查询占位记录出参
 * 
 * @author zhairongping
 *
 */
public class StockOccupyOutputVo {
    private Integer count;

    private List<StockOccupyInfoOutput> rows = new ArrayList<StockOccupyInfoOutput>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<StockOccupyInfoOutput> getRows() {
        return rows;
    }

    public void setRows(List<StockOccupyInfoOutput> rows) {
        this.rows = rows;
    }
}
